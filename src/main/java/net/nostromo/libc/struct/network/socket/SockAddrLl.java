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

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Struct;

// sockaddr_ll (linux/if_packet.h)
public class SockAddrLl extends Struct {

    // total bytes
    public static final int SIZE = 20;

    public short sll_family;   // u16
    public short sll_protocol; // ube16
    public int sll_ifindex;    // 32
    public short sll_hatype;   // u16
    public byte sll_pkttype;   // u8
    public byte sll_halen;     // u8
    public byte[] sll_addr;    // u8[8]

    public SockAddrLl() {
        super(SIZE);
    }

    @Override
    public void instantiateObjects() {
        sll_addr = new byte[8];
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {
        sll_family = buffer.getShort();
        sll_protocol = buffer.getNetworkShort();
        sll_ifindex = buffer.getInt();
        sll_hatype = buffer.getShort();
        sll_pkttype = buffer.getByte();
        sll_halen = buffer.getByte();
        buffer.getBytes(sll_addr);
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        buffer.setShort(sll_family);
        buffer.setNetworkShort(sll_protocol);
        buffer.setInt(sll_ifindex);
        buffer.setShort(sll_hatype);
        buffer.setByte(sll_pkttype);
        buffer.setByte(sll_halen);
        buffer.setBytes(sll_addr);
    }
}
