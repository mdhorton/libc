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

    public static final int SIZE = 40;

    // total 40 bytes
    public int version;             // 32
    public int offset_to_priv;      // 32
    public int block_status;        // 32
    public int num_packets;         // 32
    public int offset_to_first_pkt; // 32
    public int block_len;           // 32
    public long seq_num;            // 64
    public int ts_sec;              // 32
    public int ts_un_sec;           // 32

    @Override
    void init(final NativeHeapBuffer buffer) {
        version = buffer.getInt();
        offset_to_priv = buffer.getInt();
        block_status = buffer.getInt();
        num_packets = buffer.getInt();
        offset_to_first_pkt = buffer.getInt();
        block_len = buffer.getInt();
        seq_num = buffer.getLong();
        ts_sec = buffer.getInt();
        ts_un_sec = buffer.getInt();
    }

    @Override
    public String toString() {
        return "TP_BLOCK_HDR" +
                "  version: " + Integer.toUnsignedLong(version) +
                "  offset_to_priv: " + Integer.toUnsignedLong(offset_to_priv) +
                "  block_status: " + Integer.toUnsignedLong(block_status) +
                "  num_packets: " + Integer.toUnsignedLong(num_packets) +
                "  offset_to_first_pkt: " + Integer.toUnsignedLong(offset_to_first_pkt) +
                "  block_len: " + Integer.toUnsignedLong(block_len) +
                "  seq_num: " + BigInteger.valueOf(seq_num) +
                "  ts_sec: " + Integer.toUnsignedLong(ts_sec) +
                "  ts_un_sec: " + Integer.toUnsignedLong(ts_un_sec);
    }
}
