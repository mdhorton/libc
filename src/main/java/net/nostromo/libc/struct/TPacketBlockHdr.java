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

package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

import java.math.BigInteger;

public class TPacketBlockHdr extends JavaStruct {

    // total 40 bytes
    public static final int SIZE = 40;

    // tpacket_block_desc (linux/if_packet.h)
    public int version;             // u32
    public int offset_to_priv;      // u32

    // union tpacket_bd_header_u -> tpacket_hdr_v1
    public int block_status;        // u32
    public int num_packets;         // u32
    public int offset_to_first_pkt; // u32
    public int block_len;           // u32
    public long seq_num;            // u64

    // tpacket_bd_ts
    public int first_ts_sec;        // u32
    public int first_ts_un_sec;     // u32
    public int last_ts_sec;         // u32
    public int last_ts_un_sec;      // u32

    @Override
    void read(final NativeHeapBuffer buffer) {
        version = buffer.getInt();
        offset_to_priv = buffer.getInt();
        block_status = buffer.getInt();
        num_packets = buffer.getInt();
        offset_to_first_pkt = buffer.getInt();
        block_len = buffer.getInt();
        seq_num = buffer.getLong();
        first_ts_sec = buffer.getInt();
        first_ts_un_sec = buffer.getInt();
        last_ts_sec = buffer.getInt();
        last_ts_un_sec = buffer.getInt();
    }

    @Override
    void write(final NativeHeapBuffer buffer) {
        buffer.setInt(version);
        buffer.setInt(offset_to_priv);
        buffer.setInt(block_status);
        buffer.setInt(num_packets);
        buffer.setInt(offset_to_first_pkt);
        buffer.setInt(block_len);
        buffer.setLong(seq_num);
        buffer.setInt(first_ts_sec);
        buffer.setInt(first_ts_un_sec);
        buffer.setInt(last_ts_sec);
        buffer.setInt(last_ts_un_sec);
    }

    @Override
    public String toString() {
        return String.format("ver: %d  priv: %d  status: %d  cnt: %d  offset: %d  len: %d  seq: %d  %d.%d -> %d.%d",
                Integer.toUnsignedLong(version),
                Integer.toUnsignedLong(offset_to_priv),
                Integer.toUnsignedLong(block_status),
                Integer.toUnsignedLong(num_packets),
                Integer.toUnsignedLong(offset_to_first_pkt),
                Integer.toUnsignedLong(block_len),
                BigInteger.valueOf(seq_num),
                Integer.toUnsignedLong(first_ts_sec),
                Integer.toUnsignedLong(first_ts_un_sec),
                Integer.toUnsignedLong(last_ts_sec),
                Integer.toUnsignedLong(last_ts_un_sec));
    }
}
