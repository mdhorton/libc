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

package net.nostromo.libc.struct.network.socket;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// sockaddr_ll (linux/if_packet.h)
public class SockAddrLl extends Struct {

    // total bytes
    public static final int BYTES = 20;

    public short family;   // u16
    public short protocol; // ube16
    public int ifindex;    // 32
    public short hatype;   // u16
    public byte pkttype;   // u8
    public byte halen;     // u8
    public byte[] addr;    // u8[8]

    public SockAddrLl() {
        super(BYTES);
    }

    @Override
    public void instantiateObjects() {
        addr = new byte[8];
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        family = buffer.getShort();
        protocol = buffer.getNetworkShort();
        ifindex = buffer.getInt();
        hatype = buffer.getShort();
        pkttype = buffer.getByte();
        halen = buffer.getByte();
        buffer.getBytes(addr);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setShort(family);
        buffer.setNetworkShort(protocol);
        buffer.setInt(ifindex);
        buffer.setShort(hatype);
        buffer.setByte(pkttype);
        buffer.setByte(halen);
        buffer.setBytes(addr);
    }
}
