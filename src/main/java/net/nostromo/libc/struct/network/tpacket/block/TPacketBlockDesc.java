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

package net.nostromo.libc.struct.network.tpacket.block;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// tpacket_block_desc (linux/if_packet.h)
public class TPacketBlockDesc extends Struct {

    // total bytes
    public static final int SIZE = 48;

    public int version;          // u32
    public int offset_to_priv;   // u32
    public TPacketBdHeaderU hdr_u;

    public TPacketBlockDesc(final OffHeapBuffer buffer, final TPacketBdHeaderU.Name name) {
        super(buffer);
        hdr_u = new TPacketBdHeaderU(name);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        version = buffer.getInt();
        offset_to_priv = buffer.getInt();
        hdr_u.read(buffer);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(version);
        buffer.setInt(offset_to_priv);
        hdr_u.write(buffer);
    }

    @Override
    public String toString() {
        return String.format("ver: %d  priv: %d  %s", Integer.toUnsignedLong(version),
                Integer.toUnsignedLong(offset_to_priv), hdr_u.toString());
    }
}
