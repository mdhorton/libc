package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class TcpHdr extends JavaStruct {

    // total 20 bytes
    // can have up to 40 more bytes of optional headers
    public short src_port; // 16
    public short dst_port; // 16
    public int seq;        // 32
    public int ack_seq;    // 32
    public byte data_off;  // 4
    public byte reserved;  // 3
    public byte ns;        // 1
    public byte cwr;       // 1
    public byte ece;       // 1
    public byte urg;       // 1
    public byte ack;       // 1
    public byte psh;       // 1
    public byte rst;       // 1
    public byte syn;       // 1
    public byte fin;       // 1
    public short wndw_sz;  // 16
    public short chk_sum;  // 16
    public short urg_ptr;  // 16

    @Override
    void init(final NativeHeapBuffer buffer) {
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

        // data_off specifies the number of 32-bit (4 byte) words,
        // so we must multiply by 4 to get the total number of bytes
        data_off *= 4;
    }

    @Override
    public String toString() {
        return "        " +
                "TCP_HDR" +
                "  src_port: " + Short.toUnsignedInt(src_port) +
                "  dst_port: " + Short.toUnsignedInt(dst_port) +
                "  seq: " + Integer.toUnsignedLong(seq) +
                "  ack_seq: " + Integer.toUnsignedLong(ack_seq) +
                "  data_off: " + data_off +
                "  reserved: " + reserved +
                "  ns: " + ns +
                "  cwr: " + cwr +
                "  ece: " + ece +
                "  urg: " + urg +
                "  ack: " + ack +
                "  psh: " + psh +
                "  rst: " + rst +
                "  syn: " + syn +
                "  fin: " + fin +
                "  wndw_sz: " + Short.toUnsignedInt(wndw_sz) +
                "  chk_sum: " + Short.toUnsignedInt(chk_sum) +
                "  urg_ptr: " + Short.toUnsignedInt(urg_ptr);
    }
}
