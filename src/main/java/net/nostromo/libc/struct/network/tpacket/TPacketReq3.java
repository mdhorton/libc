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

package net.nostromo.libc.struct.network.tpacket;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.struct.Struct;

// tpacket_req3 (linux/if_packet.h)
public class TPacketReq3 extends Struct {

    // total bytes
    public static final int SIZE = 28;

    public int block_size;       // u32 (must be multiple of PAGE_SIZE, and should be power of 2 or space is wasted)
    public int block_nr;         // u32
    public int frame_size;       // u32 (should always be a multiple of TPACKET_ALIGNMENT)
    public int frame_nr;         // u32 (must be (block_size / frame_size) * block_nr)
    public int retire_blk_tov;   // u32 (milliseconds)
    public int sizeof_priv;      // u32
    public int feature_req_word; // u32

    public TPacketReq3() {
        super(SIZE);
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {
        block_size = buffer.getInt();
        block_nr = buffer.getInt();
        frame_size = buffer.getInt();
        frame_nr = buffer.getInt();
        retire_blk_tov = buffer.getInt();
        sizeof_priv = buffer.getInt();
        feature_req_word = buffer.getInt();
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        buffer.setInt(block_size);
        buffer.setInt(block_nr);
        buffer.setInt(frame_size);
        buffer.setInt(frame_nr);
        buffer.setInt(retire_blk_tov);
        buffer.setInt(sizeof_priv);
        buffer.setInt(feature_req_word);
    }
}
