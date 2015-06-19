package net.nostromo.libc;

import com.sun.jna.Pointer;
import net.nostromo.libc.c.pollfd;
import net.nostromo.libc.model.TPacket3Hdr;
import net.nostromo.libc.model.TPacketBlockHdr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public abstract class PacketRxLoop implements LibcConstants {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final Unsafe unsafe = TheUnsafe.UNSAFE;

    protected final Pointer mmap;
    protected final int fd;
    protected final int blockSize;
    protected final int blockCnt;
    protected final byte[] blockBytes;
    protected final long mmapLoc;

    protected final TPacketBlockHdr blockHdr = new TPacketBlockHdr();
    protected final TPacket3Hdr tp3hdr = new TPacket3Hdr();

    protected final int blockHdrSize = 48;
    protected final int tp3HdrSize = 48;

    protected int ok = 3;

    public PacketRxLoop(final Pointer mmap, final int fd, final int blockSize, final int blockCnt) {
        this.mmap = mmap;
        this.fd = fd;
        this.blockSize = blockSize;
        this.blockCnt = blockCnt;
        mmapLoc = Pointer.nativeValue(mmap);
        blockBytes = new byte[blockSize];
    }

    public void loop() {
        final pollfd pollfd = new pollfd();
        pollfd.fd = fd;
        pollfd.events = POLLIN | POLLERR;
        pollfd.revents = 0;
        pollfd.write();

        int blockIdx = 0;

        while (true) {
            final long offset = (long) blockSize * blockIdx;
            final long statusOffset = offset + 8;

            // poll the block until kernel is finished
            while ((mmap.getInt(statusOffset) & TP_STATUS_USER) == 0) {
                LIBC.poll(pollfd, 1, -1);
            }

            walkBlock(offset);

            // return the block to the kernel
            mmap.setInt(statusOffset, TP_STATUS_KERNEL);

            // next block
            blockIdx++;
            if (blockIdx == blockCnt) blockIdx = 0;
        }
    }

    protected void walkBlock(final long blockHdrOffset) {
        long baseOffset = unsafe.arrayBaseOffset(byte[].class);
        unsafe.copyMemory(null, mmapLoc + blockHdrOffset, blockBytes, baseOffset, blockSize);

        log.info("{}", unsafe.getInt(blockBytes, baseOffset + 16));
        log.info("packet offset: {}", getInt(16));

        long packetOffset = blockHdr.offsetToFirstPkt;

        for (int idx = 0; idx < blockHdr.numPackets; idx++) {
            unsafe.copyMemory(blockBytes, packetOffset, tp3hdr, 0, tp3HdrSize);
            handlePacket();
            packetOffset += tp3hdr.tp_next_offset;
        }
    }

    protected void handlePacket() {
        log.info("got packet");
    }

    private int getInt(final int idx) {
        return (((blockBytes[idx + 3]) << 24) |
                ((blockBytes[idx + 2] & 0xff) << 16) |
                ((blockBytes[idx + 1] & 0xff) << 8) |
                ((blockBytes[idx] & 0xff)));
    }

//    protected abstract void captureIpPacket(final ethhdr ethhdr);
}
