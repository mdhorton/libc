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
import net.nostromo.libc.Struct;

public class TPacketHdrV1 extends Struct {

    // total bytes
    public static final int SIZE = 40;

    // tpacket_hdr_v1 (linux/if_packet.h)
    public int block_status;         // u32
    public int num_pkts;             // u32
    public int offset_to_first_pkt;  // u32
    public int blk_len;              // u32
    public long seq_num;             // u64 (aligned)
    public TPacketBdTs ts_first_pkt;
    public TPacketBdTs ts_last_pkt;

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        block_status = buffer.getInt();
        num_pkts = buffer.getInt();
        offset_to_first_pkt = buffer.getInt();
        blk_len = buffer.getInt();
        seq_num = buffer.getLong();
        ts_first_pkt.read(buffer);
        ts_last_pkt.read(buffer);
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        buffer.setInt(block_status);
        buffer.setInt(num_pkts);
        buffer.setInt(offset_to_first_pkt);
        buffer.setInt(blk_len);
        buffer.setLong(seq_num);
        ts_first_pkt.write(buffer);
        ts_last_pkt.write(buffer);
    }
}
