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

import net.nostromo.libc.Struct;
import net.nostromo.libc.NativeHeapBuffer;

public class TPacketHdrVariant1 extends Struct {

    // total bytes
    public static final int SIZE = 12;

    // tpacket_hdr_variant1 (linux/if_packet.h)
    public int tp_rxhash;      // u32
    public int tp_vlan_tci;    // u32
    public short tp_vlan_tpid; // u16
    public short tp_padding;   // u16

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        tp_rxhash = buffer.getInt();
        tp_vlan_tci = buffer.getInt();
        tp_vlan_tpid = buffer.getShort();
        tp_padding = buffer.getShort();
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        buffer.setInt(tp_rxhash);
        buffer.setInt(tp_vlan_tci);
        buffer.setShort(tp_vlan_tpid);
        buffer.setShort(tp_padding);
    }
}
