package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Util;

public class IpHdr extends JavaStruct {

    // total 20 bytes
    public byte version;   // 4
    public byte hdr_len;   // 4
    public byte dscp;      // 6
    public byte ecn;       // 2
    public short tot_len;  // 16
    public short id;       // 16
    public byte flags;     // 3
    public short frag_off; // 13
    public byte ttl;       // 8
    public byte protocol;  // 8
    public short chk_sum;  // 16
    public int src_ip;     // 32
    public int dst_ip;     // 32

    @Override
    void init(final NativeHeapBuffer buffer) {
        {
            final byte b = buffer.getByte();
            version = (byte) ((b >>> 4) & 0xF); // shift right 4, get 4
            hdr_len = (byte) (b & 0xF);         // get 4 (then multiply by 4)
        }

        {
            final byte b = buffer.getByte();
            dscp = (byte) ((b >>> 2) & 0x3F);   // shift right 2, get 6
            ecn = (byte) (b & 0x3);             // get 2
        }

        tot_len = buffer.getNetworkShort();
        id = buffer.getNetworkShort();

        final short s = buffer.getNetworkShort();
        flags = (byte) ((s >>> 13) & 0x7); // shift right 13, get 3
        frag_off = (short) (s & 0x1FFF);   // get 13

        ttl = buffer.getByte();
        protocol = buffer.getByte();
        chk_sum = buffer.getNetworkShort();
        src_ip = buffer.getNetworkInt();
        dst_ip = buffer.getNetworkInt();

        // hdr_len is in 4 byte words, so we must multiply by 4
        hdr_len *= 4;
    }

    @Override
    public String toString() {
        return "      " +
                "IP_HDR" +
                "  version: " + version +
                "  hdr_len: " + hdr_len +
                "  dscp: " + dscp +
                "  ecn: " + ecn +
                "  tot_len: " + Short.toUnsignedInt(tot_len) +
                "  id: " + Short.toUnsignedInt(id) +
                "  flags: " + flags +
                "  frag_off: " + frag_off +
                "  ttl: " + Byte.toUnsignedInt(ttl) +
                "  protocol: " + Byte.toUnsignedInt(protocol) +
                "  chk_sum: " + Short.toUnsignedInt(chk_sum) +
                "  src_ip: " + Util.inetNtoa(src_ip) +
                "  dst_ip: " + Util.inetNtoa(dst_ip);
    }
}
