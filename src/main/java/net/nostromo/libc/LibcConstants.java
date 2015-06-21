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

import com.sun.jna.Native;

public interface LibcConstants {

    Libc LIBC = (Libc) Native.loadLibrary("c", Libc.class);

    byte ZERO_BYTE = (byte) 0;
    byte ZERO_SHORT = (short) 0;

    byte IPPROTO_ICMP = 1;
    byte IPPROTO_IGMP = 2;
    byte IPPROTO_TCP = 6;
    byte IPPROTO_UDP = 17;
    byte IPPROTO_MTP = 92;

    int PF_PACKET = 17;
    int SOCK_RAW = 3;

    int ETH_HLEN = 14;

    int ETH_P_ALL = 0x0003;
    int ETH_P_IP = 0x0800;
    int ETH_P_IPV6 = 0x86DD;
    int ETH_P_ARP = 0x0806;
    int ETH_P_RARP = 0x8035;

    int SOL_PACKET = 263;
    int PACKET_VERSION = 10;
    int TPACKET_V3 = 2;

    int PACKET_RX_RING = 5;
    int TP_FT_REQ_FILL_RXHASH = 0x1;

    int PROT_READ = 0x1;
    int PROT_WRITE = 0x2;

    int MAP_SHARED = 0x01;
    int MAP_LOCKED = 0x02000;
    int MAP_NORESERVE = 0x04000;

    int PACKET_FANOUT = 18;
    int PACKET_FANOUT_HASH = 0;
    int PACKET_FANOUT_LB = 1;
    int PACKET_FANOUT_CPU = 2;
    int PACKET_FANOUT_ROLLOVER = 3;
    int PACKET_FANOUT_RND = 4;
    int PACKET_FANOUT_QM = 5;
    int PACKET_FANOUT_FLAG_ROLLOVER = 0x1000;
    int PACKET_FANOUT_FLAG_DEFRAG = 0x8000;

    int POLLIN = 0x001;
    int POLLERR = 0x008;

    int TP_STATUS_KERNEL = 0;
    int TP_STATUS_USER = 1;

    // file open flags
    int O_RDONLY = 00;
    int O_WRONLY = 01;
    int O_RDWR = 02;
    int O_CREAT = 0100;
    int O_EXCL = 0200;
    int O_NOCTTY = 0400;
    int O_TRUNC = 01000;
    int O_APPEND = 02000;
    int O_NONBLOCK = 04000;

    // file modes
    int S_IRUSR = 0400;
    int S_IWUSR = 0200;
    int S_IXUSR = 0100;
    int S_IRWXU = S_IRUSR | S_IWUSR | S_IXUSR;
    int S_IRGRP = S_IRUSR >> 3;
    int S_IWGRP = S_IWUSR >> 3;
    int S_IXGRP = S_IXUSR >> 3;
    int S_IRWXG = S_IRWXU >> 3;
    int S_IROTH = S_IRGRP >> 3;
    int S_IWOTH = S_IWGRP >> 3;
    int S_IXOTH = S_IXGRP >> 3;
    int S_IRWXO = S_IRWXG >> 3;

    // file seek
    int SEEK_SET = 0;
    int SEEK_CUR = 1;
    int SEEK_END = 2;
    int SEEK_DATA = 3;
    int SEEK_HOLE = 4;
    int SEEK_MAX = SEEK_HOLE;

    // different definitions on various headers
    int __NR_gettid = 186;
}
