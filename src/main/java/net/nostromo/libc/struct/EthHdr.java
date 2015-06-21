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
    public byte[] dst_mac = new byte[6]; // 8[6]
    public byte[] src_mac = new byte[6]; // 8[6]
    public short eth_type;               // 16

    @Override
    void init(final NativeHeapBuffer buffer) {
        buffer.getBytes(dst_mac);
        buffer.getBytes(src_mac);
        eth_type = buffer.getNetworkShort();
    }

    @Override
    public String toString() {
        return "    " +
                "ETH_HDR" +
                "  dst_mac: " + Util.bytesToMac(dst_mac) +
                "  src_mac: " + Util.bytesToMac(src_mac) +
                "  eth_type: " + Short.toUnsignedInt(eth_type);
    }
}
