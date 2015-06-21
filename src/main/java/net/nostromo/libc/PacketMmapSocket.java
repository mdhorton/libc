package net.nostromo.libc;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import net.nostromo.libc.c.tpacket_req3;

public class PacketMmapSocket extends Socket {

    public PacketMmapSocket(final int domain, final int type, final int protocol) {
        super(domain, type, protocol);
    }

    public void setupTPacketV3() {
        final IntByReference ref = new IntByReference(TPACKET_V3);
        LIBC.setsockopt(fd, SOL_PACKET, PACKET_VERSION, ref.getPointer(), Util.SIZE_OF_INT);
    }

    public void setupPacketRxRing(final tpacket_req3 req3) {
        LIBC.setsockopt(fd, SOL_PACKET, PACKET_RX_RING, req3.getPointer(), req3.size());
    }

    public void setupPacketFanout(final int fanoutType) {
        final int pid = LIBC.getpid();
        setupPacketFanout(fanoutType, pid);
    }

    public void setupPacketFanout(final int fanoutType, final int pid) {
        final int fanoutVal = ((pid & 0xffff) | (fanoutType << 16));
        final IntByReference ref = new IntByReference(fanoutVal);
        LIBC.setsockopt(fd, SOL_PACKET, PACKET_FANOUT, ref.getPointer(), Util.SIZE_OF_INT);
    }

    public Pointer mmap(final long mapSize) {
        return mmap(mapSize, 0);
    }

    public Pointer mmap(final long mapSize, final int extraFlags) {
        final Pointer p0 = new Pointer(0);
        return LIBC.mmap(p0, mapSize, PROT_READ | PROT_WRITE, MAP_SHARED | extraFlags, fd, 0);
    }
}
