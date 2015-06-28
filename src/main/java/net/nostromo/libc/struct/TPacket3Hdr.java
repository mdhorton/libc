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

public class TPacket3Hdr extends Struct {

    // total bytes
    public static final int SIZE = 48;

    // tpacket3_hdr (linux/if_packet.h)
    public int tp_next_offset;              // u32
    public int tp_sec;                      // u32
    public int tp_nsec;                     // u32
    public int tp_snaplen;                  // u32
    public int tp_len;                      // u32
    public int tp_status;                   // u32
    public short tp_mac;                    // u16
    public short tp_net;                    // u16
    public TPacketHdrVariant1 hv1;
    public byte[] tp_padding = new byte[8]; // u8[8]

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        tp_next_offset = buffer.getInt();
        tp_sec = buffer.getInt();
        tp_nsec = buffer.getInt();
        tp_snaplen = buffer.getInt();
        tp_len = buffer.getInt();
        tp_status = buffer.getInt();
        tp_mac = buffer.getShort();
        tp_net = buffer.getShort();
        hv1.read(buffer);
        buffer.getBytes(tp_padding);
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        buffer.setInt(tp_next_offset);
        buffer.setInt(tp_sec);
        buffer.setInt(tp_nsec);
        buffer.setInt(tp_snaplen);
        buffer.setInt(tp_len);
        buffer.setInt(tp_status);
        buffer.setShort(tp_mac);
        buffer.setShort(tp_net);
        hv1.write(buffer);
        buffer.setBytes(tp_padding);
    }

    @Override
    public String toString() {
        return String.format(
                "%d.%d  next: %d  len: %d (%d)  status: %d  mac: %d  net: %d",
                Integer.toUnsignedLong(tp_sec), Integer.toUnsignedLong(tp_nsec),
                Integer.toUnsignedLong(tp_next_offset),
                Integer.toUnsignedLong(tp_len), Integer.toUnsignedLong(tp_snaplen),
                Integer.toUnsignedLong(tp_status),
                Short.toUnsignedInt(tp_mac),
                Short.toUnsignedInt(tp_net));
    }
}
