package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class EthHdr extends JavaStruct {

    public byte[] h_dest = new byte[6];   // u8[6]
    public byte[] h_source = new byte[6]; // u8[6]
    public short h_proto;                 // be16 (u16)

    @Override
    void init(final NativeHeapBuffer buffer) {
        buffer.getBytes(h_dest);
        buffer.getBytes(h_source);
        h_proto = buffer.getNetworkShort();
    }
}
