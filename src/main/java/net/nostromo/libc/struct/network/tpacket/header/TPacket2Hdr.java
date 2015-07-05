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

// tpacket2_hdr (linux/if_packet.h)
public class TPacket2Hdr extends Struct {

    // TPACKET2_HDRLEN = 52 bytes
    // tpacket2_hdr (32) + sockaddr_ll (20) = 52
    // then 14 byte gap
    // ethhdr (mac) usually offset 66
    // iphdr  (net) usually offset 80

    // total bytes
    public static final int BYTES = 32;

    public int status;      // u32
    public int len;         // u32
    public int snaplen;     // u32
    public short mac;       // u16
    public short net;       // u16
    public int sec;         // u32
    public int nsec;        // u32
    public short vlan_tci;  // u16
    public short vlan_tpid; // u16
    public byte[] padding;  // u8[4]

    public TPacket2Hdr(final OffHeapBuffer buffer) {
        super(buffer);
    }

    @Override
    public void instantiateObjects() {
        padding = new byte[4];
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        status = buffer.getInt();
        len = buffer.getInt();
        snaplen = buffer.getInt();
        mac = buffer.getShort();
        net = buffer.getShort();
        sec = buffer.getInt();
        nsec = buffer.getInt();
        vlan_tci = buffer.getShort();
        vlan_tpid = buffer.getShort();
        buffer.getBytes(padding);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(status);
        buffer.setInt(len);
        buffer.setInt(snaplen);
        buffer.setShort(mac);
        buffer.setShort(net);
        buffer.setInt(sec);
        buffer.setInt(nsec);
        buffer.setShort(vlan_tci);
        buffer.setShort(vlan_tpid);
        buffer.setBytes(padding);
    }

    @Override
    public String toString() {
        return String.format("%d.%d  len: %d (%d)  status: %d  mac: %d  net: %d",
                Integer.toUnsignedLong(sec), Integer.toUnsignedLong(nsec),
                Integer.toUnsignedLong(len), Integer.toUnsignedLong(snaplen),
                Integer.toUnsignedLong(status), Short.toUnsignedInt(mac), Short.toUnsignedInt(net));
    }
}
