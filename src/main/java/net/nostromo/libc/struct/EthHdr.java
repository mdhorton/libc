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

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Util;

public class EthHdr extends JavaStruct {

    // total 14 bytes
    public static final int SIZE = 14;

    // ethhdr (linux/if_ether.h)
    public byte[] dst_mac = new byte[6]; // u8[6]
    public byte[] src_mac = new byte[6]; // u8[6]
    public short eth_type;               // ube16

    @Override
    void read(final NativeHeapBuffer buffer) {
        buffer.getBytes(dst_mac);
        buffer.getBytes(src_mac);
        eth_type = buffer.getNetworkShort();
    }

    @Override
    void write(final NativeHeapBuffer buffer) {
        buffer.setBytes(dst_mac);
        buffer.setBytes(src_mac);
        buffer.setNetworkShort(eth_type);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", Util.bytesToMac(src_mac), Util.bytesToMac(dst_mac));
    }
}
