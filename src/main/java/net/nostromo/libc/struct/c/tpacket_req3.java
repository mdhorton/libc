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

package net.nostromo.libc.struct.c;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class tpacket_req3 extends Structure {

    public int tp_block_size;
    public int tp_block_nr;
    public int tp_frame_size;
    public int tp_frame_nr;
    public int tp_retire_blk_tov;
    public int tp_sizeof_priv;
    public int tp_feature_req_word;

    public tpacket_req3() { }

    public tpacket_req3(final Pointer p) {
        super(p);
    }

    protected List<?> getFieldOrder() {
        return Arrays.asList("tp_block_size", "tp_block_nr", "tp_frame_size", "tp_frame_nr", "tp_retire_blk_tov",
                "tp_sizeof_priv", "tp_feature_req_word");
    }
}
