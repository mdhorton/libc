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

package net.nostromo.libc.struct.network.tpacket.header;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// tpacket_hdr_variant1 (linux/if_packet.h)
public class TPacketHdrVariant1 extends Struct {

    // total bytes
    public static final int BYTES = 12;

    public int rxhash;      // u32
    public int vlan_tci;    // u32
    public short vlan_tpid; // u16
    public short padding;   // u16

    @Override
    public void read(final OffHeapBuffer buffer) {
        rxhash = buffer.getInt();
        vlan_tci = buffer.getInt();
        vlan_tpid = buffer.getShort();
        padding = buffer.getShort();
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(rxhash);
        buffer.setInt(vlan_tci);
        buffer.setShort(vlan_tpid);
        buffer.setShort(padding);
    }
}
