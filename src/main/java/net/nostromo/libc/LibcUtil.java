package net.nostromo.libc;

import net.nostromo.libc.struct.IntRef;
import net.nostromo.libc.struct.Struct;
import net.nostromo.libc.struct.network.PacketMreq;
import net.nostromo.libc.struct.network.ifreq.IfReq;
import net.nostromo.libc.struct.network.ifreq.IfReqRnUnion;
import net.nostromo.libc.struct.network.ifreq.IfReqRuUnion;
import net.nostromo.libc.struct.network.socket.SockFProg;
import net.nostromo.libc.struct.system.CpuSetT;

public class LibcUtil implements LibcConstants {

    private static final Libc libc = Libc.libc;

    public static final LibcUtil util = new LibcUtil();

    private LibcUtil() {}

    public void setCpu(final int cpu) {
        setCpu(0, cpu);
    }

    public void setCpu(final int pid, final int cpu) {
        final CpuSetT cpuSet = new CpuSetT();
        cpuSet.CPU_SET(cpu);
        libc.sched_setaffinity(pid, CpuSetT.BYTES, cpuSet.pointer());
    }

    public void enablePromiscMode(final int sock, final String ifName) {
        final PacketMreq mreq = new PacketMreq();
        mreq.ifindex = getInterfaceId(sock, ifName);
        mreq.type = (short) PACKET_MR_PROMISC;
        libc.setsockopt(sock, SOL_PACKET, PACKET_ADD_MEMBERSHIP, mreq.pointer(), PacketMreq.BYTES);
    }

    public void disablePromiscMode(final int sock, final String ifName) {
        final PacketMreq mreq = new PacketMreq();
        mreq.ifindex = getInterfaceId(sock, ifName);
        mreq.type = (short) PACKET_MR_PROMISC;
        libc.setsockopt(sock, SOL_PACKET, PACKET_DROP_MEMBERSHIP, mreq.pointer(), PacketMreq.BYTES);
    }

    public void enablePromiscModeIoctl(final int sock, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        libc.ioctl(sock, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already enabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) != 0) return;

        ifReq.ifru.flags |= IFF_PROMISC;
        libc.ioctl(sock, SIOCSIFFLAGS, ifReq.pointer());
    }

    public void disablePromiscModeIoctl(final int sock, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        libc.ioctl(sock, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already disabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) == 0) return;

        ifReq.ifru.flags &= ~IFF_PROMISC;
        libc.ioctl(sock, SIOCSIFFLAGS, ifReq.pointer());
    }

    public int getMtu(final int sock, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.MTU);
        Struct.copyString(ifReq.ifrn.name, ifName);

        libc.ioctl(sock, SIOCGIFMTU, ifReq.pointer());
        ifReq.read();

        return ifReq.ifru.mtu;
    }

    public int getInterfaceId(final int sock, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.IFINDEX);
        Struct.copyString(ifReq.ifrn.name, ifName);

        libc.ioctl(sock, SIOCGIFINDEX, ifReq.pointer());
        ifReq.read();

        return ifReq.ifru.ifindex;
    }

    // currently this only works for TPACKET_V2
    public void setPacketCopyThreshold(final int sock, final int threshold) {
        final IntRef intRef = new IntRef(threshold);
        libc.setsockopt(sock, SOL_PACKET, PACKET_COPY_THRESH, intRef.pointer(), Integer.BYTES);
    }

    public void enableQdiscBypass(final int sock) {
        final IntRef ref = new IntRef(1);
        libc.setsockopt(sock, SOL_PACKET, PACKET_QDISC_BYPASS, ref.pointer(), Integer.BYTES);
    }

    public void enablePacketLossDiscard(final int sock) {
        final IntRef ref = new IntRef(1);
        libc.setsockopt(sock, SOL_PACKET, PACKET_LOSS, ref.pointer(), Integer.BYTES);
    }

    public void attachFilter(final int sock, final SockFProg sockFProg) {
        libc.setsockopt(sock, SOL_PACKET, SO_ATTACH_FILTER, sockFProg.pointer(), SockFProg.BYTES);
    }

    public int getReceiveBufferSize(final int sock) {
        final IntRef optval = new IntRef(0);
        final IntRef optlen = new IntRef(Integer.BYTES);
        libc.getsockopt(sock, SOL_SOCKET, SO_RCVBUF, optval.pointer(), optlen.pointer());
        return optval.getValue();
    }
}
