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
import net.nostromo.libc.Struct;

// tpacket_req3 (linux/packet.h)
public class TPacketReq3 extends Struct {

    // total bytes
    public static final int SIZE = 24;

    public int tp_block_size;
    public int tp_block_nr;
    public int tp_frame_size;
    public int tp_frame_nr;
    public int tp_retire_blk_tov;
    public int tp_sizeof_priv;
    public int tp_feature_req_word;

    public TPacketReq3(final NativeHeapBuffer buffer) {
        super(buffer);
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {

    }

    @Override
    public void write(final NativeHeapBuffer buffer) {

    }
}
