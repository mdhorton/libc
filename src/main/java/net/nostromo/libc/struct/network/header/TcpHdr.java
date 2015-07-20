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

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// tcphdr (netinet/tcp.h)
// https://en.wikipedia.org/wiki/Transmission_Control_Protocol
public class TcpHdr extends Struct {

    // can have up to 40 more bytes of optional headers
    public static final int BYTES = 20;

    public short src_port; // ube16
    public short dst_port; // ube16
    public int seq;        // ube32
    public int ack_seq;    // ube32
    public byte data_off;  // ube16:4
    public byte reserved;  // ube16:3
    public byte ns;        // ube16:1
    public byte cwr;       // ube16:1
    public byte ece;       // ube16:1
    public byte urg;       // ube16:1
    public byte ack;       // ube16:1
    public byte psh;       // ube16:1
    public byte rst;       // ube16:1
    public byte syn;       // ube16:1
    public byte fin;       // ube16:1
    public short wndw_sz;  // ube16
    public short chk_sum;  // ube16
    public short urg_ptr;  // ube16

    // data_off specifies the number of 32-bit (4 byte) words,
    // so we must multiply by 4 to get the total number of bytes
    public int data_off_bytes;

    public TcpHdr(final OffHeapBuffer buffer) {
        super(buffer);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        src_port = buffer.getNetworkShort();
        dst_port = buffer.getNetworkShort();
        seq = buffer.getNetworkInt();
        ack_seq = buffer.getNetworkInt();

        final short s = buffer.getNetworkShort();
        data_off = (byte) ((s >>> 12) & 0xF); // shift right 12 bits, get 4 bits
        reserved = (byte) ((s >>> 9) & 0x7);  // shift right 9 bits, get 3 bits
        ns = (byte) ((s >>> 8) & 0x1);        // get bit 8-16 individually...
        cwr = (byte) ((s >>> 7) & 0x1);
        ece = (byte) ((s >>> 6) & 0x1);
        urg = (byte) ((s >>> 5) & 0x1);
        ack = (byte) ((s >>> 4) & 0x1);
        psh = (byte) ((s >>> 3) & 0x1);
        rst = (byte) ((s >>> 2) & 0x1);
        syn = (byte) ((s >>> 1) & 0x1);
        fin = (byte) (s & 0x1);

        wndw_sz = buffer.getNetworkShort();
        chk_sum = buffer.getNetworkShort();
        urg_ptr = buffer.getNetworkShort();

        data_off_bytes = data_off * 4;
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setNetworkShort(src_port);
        buffer.setNetworkShort(dst_port);
        buffer.setNetworkInt(seq);
        buffer.setNetworkInt(ack_seq);

        buffer.setNetworkShort(
                (short) (data_off << 12 | reserved << 9 | ns << 8 | cwr << 7 | ece << 6 |
                        urg << 5 | ack << 4 | psh << 3 | rst << 2 | syn << 1 | fin));

        buffer.setNetworkShort(wndw_sz);
        buffer.setNetworkShort(chk_sum);
        buffer.setNetworkShort(urg_ptr);
    }

    @Override
    public String toString() {
        return String.format(
                "%d -> %d  seq: %d  ackseq: %d  data: %d  rsvd: %d  ns: %d  cwr: %d  ece: %d" +
                        "  urg: %d  ack: %d  psh: %d  rst: %d  syn: %d  fin: %d  window: %d" +
                        "  chksum: %d  urgptr: %d", Short.toUnsignedInt(src_port),
                Short.toUnsignedInt(dst_port), Integer.toUnsignedLong(seq),
                Integer.toUnsignedLong(ack_seq), data_off, reserved, ns, cwr, ece, urg, ack, psh,
                rst, syn, fin, Short.toUnsignedInt(wndw_sz), Short.toUnsignedInt(chk_sum),
                Short.toUnsignedInt(urg_ptr));
    }
}
