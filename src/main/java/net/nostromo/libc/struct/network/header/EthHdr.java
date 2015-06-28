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

package net.nostromo.libc.struct.network.header;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Struct;
import net.nostromo.libc.Util;

// ethhdr (linux/if_ether.h)
// https://en.wikipedia.org/wiki/Ethernet_frame
public class EthHdr extends Struct {

    // total bytes
    public static final int SIZE = 14;

    public byte[] dst_mac = new byte[6]; // u8[6]
    public byte[] src_mac = new byte[6]; // u8[6]
    public short eth_type;               // ube16

    public EthHdr(final NativeHeapBuffer buffer) {
        super(buffer);
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {
        buffer.getBytes(dst_mac);
        buffer.getBytes(src_mac);
        eth_type = buffer.getNetworkShort();
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        buffer.setBytes(dst_mac);
        buffer.setBytes(src_mac);
        buffer.setNetworkShort(eth_type);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", Util.bytesToMac(src_mac), Util.bytesToMac(dst_mac));
    }
}
