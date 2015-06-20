package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class TPacket3Hdr extends JavaStruct {

    public int tp_next_offset; // u32
    public int tp_sec;         // u32
    public int tp_nsec;        // u32
    public int tp_snaplen;     // u32
    public int tp_len;         // u32
    public int tp_status;      // u32
    public short tp_mac;       // u16
    public short tp_net;       // u16

    // tpacket_hdr_variant1
    public int hv1_rxhash;         // u32
    public int hv1_vlan_tci;       // u32
    public short hv1_tp_vlan_tpid; // u16
    public short hv1_padding;      // u16

    public byte[] tp_padding = new byte[8]; // u8[8]

    @Override
    void init(final NativeHeapBuffer buffer) {
        tp_next_offset = buffer.getInt();
        tp_sec = buffer.getInt();
        tp_nsec = buffer.getInt();
        tp_snaplen = buffer.getInt();
        tp_len = buffer.getInt();
        tp_status = buffer.getInt();
        tp_mac = buffer.getShort();
        tp_net = buffer.getShort();
        hv1_rxhash = buffer.getInt();
        hv1_vlan_tci = buffer.getInt();
        hv1_tp_vlan_tpid = buffer.getShort();
        hv1_padding = buffer.getShort();
        buffer.getBytes(tp_padding);
    }
}
