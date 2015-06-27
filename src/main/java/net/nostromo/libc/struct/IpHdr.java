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

public class IpHdr extends JavaStruct {

    // total 20 bytes
    // can have up to 40 more bytes of optional headers

    // iphdr (netinet/ip.h)
    public byte version;   // u8:4
    public byte hdr_len;   // u8:4
    public byte dscp;      // u8:6
    public byte ecn;       // u8:2
    public short tot_len;  // ube16
    public short id;       // ube16
    public byte flags;     // ube16:3
    public short frag_off; // ube16:13
    public byte ttl;       // u8
    public byte protocol;  // u8
    public short chk_sum;  // ube16
    public int src_ip;     // ube32
    public int dst_ip;     // ube32

    public int hdr_len_bytes;

    @Override
    void read(final NativeHeapBuffer buffer) {
        byte b;

        b = buffer.getByte();
        version = (byte) ((b >>> 4) & 0xF); // shift right 4 bits, get 4 bits
        hdr_len = (byte) (b & 0xF);         // get 4 bits

        b = buffer.getByte();
        dscp = (byte) ((b >>> 2) & 0x3F);   // shift right 2 bits, get 6 bits
        ecn = (byte) (b & 0x3);             // get 2 bits

        tot_len = buffer.getNetworkShort();
        id = buffer.getNetworkShort();

        final short s = buffer.getNetworkShort();
        flags = (byte) ((s >>> 13) & 0x7); // shift right 13 bits, get 3 bits
        frag_off = (short) (s & 0x1FFF);   // get 13 bits

        ttl = buffer.getByte();
        protocol = buffer.getByte();
        chk_sum = buffer.getNetworkShort();
        src_ip = buffer.getNetworkInt();
        dst_ip = buffer.getNetworkInt();

        // hdr_len specifies the number of 32-bit (4 byte) words,
        // so we must multiply by 4 to get the total number of bytes
        hdr_len_bytes = hdr_len * 4;
    }

    @Override
    void write(final NativeHeapBuffer buffer) {
        buffer.setByte((byte) (version << 4 | hdr_len));
        buffer.setByte((byte) (dscp << 2 | ecn));

        buffer.setNetworkShort(tot_len);
        buffer.setNetworkShort(id);

        buffer.setNetworkShort((short) (flags << 13 | frag_off));

        buffer.setByte(ttl);
        buffer.setByte(protocol);
        buffer.setNetworkShort(chk_sum);
        buffer.setNetworkInt(src_ip);
        buffer.setNetworkInt(dst_ip);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s  ver: %d  len: %d (%d)  dscp: %d  ecn: %d  id: %d  " +
                        "flags: %d  frag: %d  ttl: %d  proto: %d  chksum: %d",
                Util.inetNtoA(src_ip), Util.inetNtoA(dst_ip),
                version,
                Short.toUnsignedInt(tot_len), hdr_len,
                dscp,
                ecn,
                Short.toUnsignedInt(id),
                flags,
                frag_off,
                Byte.toUnsignedInt(ttl),
                Byte.toUnsignedInt(protocol),
                Short.toUnsignedInt(chk_sum));
    }
}
