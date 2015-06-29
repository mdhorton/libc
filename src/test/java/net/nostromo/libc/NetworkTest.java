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

import net.nostromo.libc.io.LibcIo;
import net.nostromo.libc.io.LibcIoConstants;
import net.nostromo.libc.network.LibcNetwork;
import net.nostromo.libc.network.LibcNetworkConstants;
import net.nostromo.libc.struct.network.socket.SockAddrLl;
import net.nostromo.libc.struct.network.tpacket.TPacketReq3;

public class NetworkTest implements LibcNetworkConstants, LibcIoConstants {

    public static void main(String[] args) throws Exception {
        final LibcNetwork net = new LibcNetwork();
        final LibcIo io = new LibcIo();

        final String ifName = "lo";
        final int timeout = 100; // milliseconds
        final int blockSize = 1 << 20;
        final int blockCnt = 1 << 10;

        final int frameSize = 1 << 8;
        final int framesPerBlock = blockSize / frameSize;
        final long mapSize = (long) blockSize * blockCnt;

        final IntRef tpv3 = new IntRef(TPACKET_V3);
        final TPacketReq3 tpReq3 = new TPacketReq3();
        tpReq3.block_size = blockSize;
        tpReq3.block_nr = blockCnt;
        tpReq3.frame_size = frameSize;
        tpReq3.frame_nr = (blockSize / frameSize) * blockCnt;
        tpReq3.retire_blk_tov = timeout;
        tpReq3.sizeof_priv = 0;
        tpReq3.feature_req_word = 0;

        final int fd = net.socket(PF_PACKET, SOCK_RAW, ETH_P_ALL);
        io.disablePromiscMode(fd, ifName);

        net.setsockopt(fd, SOL_PACKET, PACKET_VERSION, tpv3.pointer(), Integer.BYTES);
        net.setsockopt(fd, SOL_PACKET, PACKET_RX_RING, tpReq3.pointer(), TPacketReq3.SIZE);

        final long mmap = io.mmap(0, mapSize, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

        final SockAddrLl sall = new SockAddrLl();
        sall.sll_family = PF_PACKET;
        sall.sll_protocol = ETH_P_ALL;
        sall.sll_ifindex = io.getInterfaceId(fd, ifName);

        net.bind(fd, sall.pointer(), SockAddrLl.SIZE);

        io.enablePromiscMode(fd, ifName);
        final int mtu = io.getMtu(fd, ifName);

        System.out.printf("mtu: %d\n", mtu);
    }
}
