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

package net.nostromo.libc.network;

public interface LibcNetworkConstants {

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

    int PACKET_FANOUT = 18;
    int PACKET_FANOUT_HASH = 0;
    int PACKET_FANOUT_LB = 1;
    int PACKET_FANOUT_CPU = 2;
    int PACKET_FANOUT_ROLLOVER = 3;
    int PACKET_FANOUT_RND = 4;
    int PACKET_FANOUT_QM = 5;
    int PACKET_FANOUT_FLAG_ROLLOVER = 0x1000;
    int PACKET_FANOUT_FLAG_DEFRAG = 0x8000;

    int TP_STATUS_KERNEL = 0;
    int TP_STATUS_USER = 1;
}
