package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class IpHdr extends JavaStruct {

    public byte ihl;       // u4
    public byte version;   // u4
    public byte tos;       // u8
    public short tot_len;  // be16 (u16)
    public short id;       // be16 (u16)
    public short frag_off; // be16 (u16)
    public byte ttl;       // u8
    public byte protocol;  // u8
    public short check;    // sum16 (u16) be16?
    public int saddr;      // be32 (u32)
    public int daddr;      // be32 (u32)

    @Override
    void init(final NativeHeapBuffer buffer) {
        final byte b = buffer.getByte();

        if (BIG_ENDIAN) {
            ihl = (byte) (b & 0x0f);    // second 4 bits
            version = (byte) (b >>> 4); // first 4 bits
        }
        else {
            ihl = (byte) (b >>> 4);      // first 4 bits
            version = (byte) (b & 0x0f); // second 4 bits
        }

        tos = buffer.getByte();
        tot_len = buffer.getNetworkShort();
        id = buffer.getNetworkShort();
        frag_off = buffer.getNetworkShort();
        ttl = buffer.getByte();
        protocol = buffer.getByte();
        check = buffer.getNetworkShort();
        saddr = buffer.getNetworkInt();
        daddr = buffer.getNetworkInt();
    }
}
