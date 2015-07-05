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

package net.nostromo.libc.struct.network;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// packet_mreq (linux/if_packet.h)
public class PacketMreq extends Struct {

    public static final int BYTES = 16;

    public int ifindex;    // 32
    public short type;     // u16
    public short alen;     // u16
    public byte[] address; // u8[8]

    public PacketMreq() {
        super(BYTES);
    }

    @Override
    public void instantiateObjects() {
        address = new byte[8];
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        ifindex = buffer.getInt();
        type = buffer.getShort();
        alen = buffer.getShort();
        buffer.getBytes(address);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(ifindex);
        buffer.setShort(type);
        buffer.setShort(alen);
        buffer.setBytes(address);
    }
}
