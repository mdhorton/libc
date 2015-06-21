/*
 * Copyright (c) 2015 Mark D. Horton
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABIL-
 * ITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.nostromo.libc;

import net.nostromo.libc.c.pollfd;
import net.nostromo.libc.struct.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public abstract class AbstractPacketRxLoop implements LibcConstants {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final Unsafe unsafe = TheUnsafe.UNSAFE;

    protected final TPacket3Hdr tp3Hdr = new TPacket3Hdr();
    protected final EthHdr ethHdr = new EthHdr();
    protected final IpHdr ipHdr = new IpHdr();
    protected final TcpHdr tcpHdr = new TcpHdr();

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
        pollfd.events = (short) (POLLIN | POLLERR);
        pollfd.revents = 0;
        pollfd.write();

        int blockIdx = 0;

        while (true) {
            final long blockStart = (long) blockSize * blockIdx;
            final long blockStatusOffset = mmapAddress + blockStart + 8; // status is 8 bytes into the block

            buffer.refill(blockStart, TPacketBlockHdr.SIZE);
            blockHdr.init(buffer, 0);

            // poll the block until the kernel gives it back to user space
            while ((blockHdr.block_status & TP_STATUS_USER) == 0) {
                LIBC.poll(pollfd, 1, -1);
                buffer.refill(blockStart, TPacketBlockHdr.SIZE);
                blockHdr.init(buffer, 0);
            }

            // fill the heap buffer from the off-heap memory
            buffer.refill(blockStart, Integer.toUnsignedLong(blockHdr.block_len));
            blockHdr.init(buffer, 0);

            // TODO: chk_sum block status

            walkBlock(blockHdr);

            // return the block to the kernel
            unsafe.putInt(blockStatusOffset, TP_STATUS_KERNEL);

            // next block
            blockIdx++;
            if (blockIdx == blockCnt) blockIdx = 0;
        }
    }

    protected void walkBlock(final TPacketBlockHdr blockHdr) {
        log.info("{}", blockHdr);
        long linkLayerOffset = blockHdr.offset_to_first_pkt;

        for (int idx = 0; idx < blockHdr.num_packets; idx++) {
            tp3Hdr.init(buffer, linkLayerOffset);
            log.info("{}", tp3Hdr);
            handlePacket(linkLayerOffset);
            linkLayerOffset += tp3Hdr.tp_next_offset;
        }
    }

    protected void handlePacket(final long linkLayerOffset) {
        long inetLayerOffset = linkLayerOffset + tp3Hdr.tp_mac;
        ethHdr.init(buffer, inetLayerOffset);
        inetLayerOffset += ETH_HLEN;
        log.info("{}", ethHdr);

        switch (Short.toUnsignedInt(ethHdr.eth_type)) {
            case ETH_P_IP:
                handleIpV4Packet(inetLayerOffset);
                break;
            case ETH_P_IPV6:
                handleIpV6Packet(inetLayerOffset);
                break;
            case ETH_P_ARP:
                handleArpPacket(inetLayerOffset);
                break;
            default:
                handleUnknownPacket(inetLayerOffset);
        }
    }

    protected void handleIpV4Packet(final long offset) {
        ipHdr.init(buffer, offset);
        final long nextOffset = offset + ipHdr.hdr_len;
        log.info("{}", ipHdr);

        switch (ipHdr.protocol) {
            case IPPROTO_TCP:
                handleTcpPacket(nextOffset);
        }
    }

    protected void handleIpV6Packet(final long offset) {
//        log.info("ipv6");
    }

    protected void handleArpPacket(final long offset) {
//        log.info("arp");
    }

    protected void handleUnknownPacket(final long offset) {
        log.info("unknown packet: {}", ethHdr.eth_type);
    }

    protected void handleTcpPacket(final long offset) {
        tcpHdr.init(buffer, offset);
        log.info("{}", tcpHdr);
    }
}
