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
