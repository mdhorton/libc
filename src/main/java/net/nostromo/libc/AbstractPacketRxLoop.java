package net.nostromo.libc;

import net.nostromo.libc.c.pollfd;
import net.nostromo.libc.struct.EthHdr;
import net.nostromo.libc.struct.TPacket3Hdr;
import net.nostromo.libc.struct.TPacketBlockHdr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public abstract class AbstractPacketRxLoop implements LibcConstants {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final Unsafe unsafe = TheUnsafe.UNSAFE;

    protected final TPacket3Hdr tp3Hdr = new TPacket3Hdr();
    protected final EthHdr ethHdr = new EthHdr();

    protected final long mmapAddress;
    protected final int sockFd;
    protected final int blockSize;
    protected final int blockCnt;
    protected final NativeHeapBuffer buffer;

    public AbstractPacketRxLoop(final long mmapAddress, final int sockFd, final int blockSize, final int blockCnt) {
        this.mmapAddress = mmapAddress;
        this.sockFd = sockFd;
        this.blockSize = blockSize;
        this.blockCnt = blockCnt;
        buffer = new NativeHeapBuffer(blockSize, mmapAddress);
    }

    public void loop() {
        final TPacketBlockHdr blockHdr = new TPacketBlockHdr();

        final pollfd pollfd = new pollfd();
        pollfd.fd = sockFd;
        pollfd.events = POLLIN | POLLERR;
        pollfd.revents = 0;
        pollfd.write();

        int blockIdx = 0;

        while (true) {
            final long blockStart = (long) blockSize * blockIdx;
            final long blockStatusOffset = mmapAddress + blockStart + 8; // skip 2 ints, so add 8 bytes

            // poll the block until the kernel gives it back to user space
            while ((unsafe.getInt(blockStatusOffset) & TP_STATUS_USER) == 0) {
                LIBC.poll(pollfd, 1, -1);
            }

            // fill the heap buffer from the native off-heap memory
            buffer.refill(blockStart);
            blockHdr.init(buffer, 0);

            walkBlock(blockHdr);

            // return the block to the kernel
            unsafe.putInt(blockStatusOffset, TP_STATUS_KERNEL);

            // next block
            blockIdx++;
            if (blockIdx == blockCnt) blockIdx = 0;
        }
    }

    protected void walkBlock(final TPacketBlockHdr blockHdr) {
        // TODO: check block status

        long packetOffset = blockHdr.offsetToFirstPkt;

        for (int idx = 0; idx < blockHdr.numPackets; idx++) {
            tp3Hdr.init(buffer, packetOffset);
            handlePacket(packetOffset);
            packetOffset += tp3Hdr.tp_next_offset;
        }
    }

    protected void handlePacket(final long packetOffset) {
        ethHdr.init(buffer, packetOffset + tp3Hdr.tp_mac);

        switch (Short.toUnsignedInt(ethHdr.h_proto)) {
            case ETH_P_IP:
                handleIpV4Packet();
                break;
            case ETH_P_IPV6:
                handleIpV6Packet();
                break;
            case ETH_P_ARP:
                handleArpPacket();
                break;
            default:
                handleUnknownPacket();
        }
    }

    protected void handleIpV4Packet() {
        log.info("ipv4");
    }

    protected void handleIpV6Packet() {
        log.info("ipv6");
    }

    protected void handleArpPacket() {
        log.info("arp");
    }

    protected void handleUnknownPacket() {
        log.info("unknown packet: {}", ethHdr.h_proto);
    }
}
