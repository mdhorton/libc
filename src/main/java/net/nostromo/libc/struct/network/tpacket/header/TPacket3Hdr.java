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

// tpacket3_hdr (linux/if_packet.h)
public class TPacket3Hdr extends Struct {

    // TPACKET2_HDRLEN = 68 bytes

    // total bytes
    public static final int BYTES = 48;

    public int next_offset;              // u32
    public int sec;                      // u32
    public int nsec;                     // u32
    public int snaplen;                  // u32
    public int len;                      // u32
    public int status;                   // u32
    public short mac;                    // u16
    public short net;                    // u16
    public TPacketHdrVariant1Union hv1_u;
    public byte[] padding = new byte[8]; // u8[8]

    public TPacket3Hdr(final OffHeapBuffer buffer, final TPacketHdrVariant1Union.Name name) {
        super(buffer);
        hv1_u = new TPacketHdrVariant1Union(name);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        next_offset = buffer.getInt();
        sec = buffer.getInt();
        nsec = buffer.getInt();
        snaplen = buffer.getInt();
        len = buffer.getInt();
        status = buffer.getInt();
        mac = buffer.getShort();
        net = buffer.getShort();
        hv1_u.read(buffer);
        buffer.getBytes(padding);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(next_offset);
        buffer.setInt(sec);
        buffer.setInt(nsec);
        buffer.setInt(snaplen);
        buffer.setInt(len);
        buffer.setInt(status);
        buffer.setShort(mac);
        buffer.setShort(net);
        hv1_u.write(buffer);
        buffer.setBytes(padding);
    }

    @Override
    public String toString() {
        return String.format("%d.%d  next: %d  len: %d (%d)  status: %d  mac: %d  net: %d",
                Integer.toUnsignedLong(sec), Integer.toUnsignedLong(nsec),
                Integer.toUnsignedLong(next_offset), Integer.toUnsignedLong(len),
                Integer.toUnsignedLong(snaplen), Integer.toUnsignedLong(status),
                Short.toUnsignedInt(mac), Short.toUnsignedInt(net));
    }
}
