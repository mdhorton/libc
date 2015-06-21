package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class TPacket3Hdr extends JavaStruct {

    // total 48 bytes
    public int tp_next_offset; // 32
    public int tp_sec;         // 32
    public int tp_nsec;        // 32
    public int tp_snaplen;     // 32
    public int tp_len;         // 32
    public int tp_status;      // 32
    public short tp_mac;       // 16
    public short tp_net;       // 16

    // tpacket_hdr_variant1
    public int hv1_rxhash;         // 32
    public int hv1_vlan_tci;       // 32
    public short hv1_tp_vlan_tpid; // 16
    public short hv1_padding;      // 16

    public byte[] tp_padding = new byte[8]; // 8[8]

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

    @Override
    public String toString() {
        return "  " +
                "TP3_HDR" +
                "  tp_next_offset: " + Integer.toUnsignedLong(tp_next_offset) +
                "  tp_sec: " + Integer.toUnsignedLong(tp_sec) +
                "  tp_nsec: " + Integer.toUnsignedLong(tp_nsec) +
                "  tp_snaplen: " + Integer.toUnsignedLong(tp_snaplen) +
                "  tp_len: " + Integer.toUnsignedLong(tp_len) +
                "  tp_status: " + Integer.toUnsignedLong(tp_status) +
                "  tp_mac: " + Short.toUnsignedInt(tp_mac) +
                "  tp_net: " + Short.toUnsignedInt(tp_net) +
                "  hv1_rxhash: " + Integer.toUnsignedLong(hv1_rxhash) +
                "  hv1_vlan_tci: " + Integer.toUnsignedLong(hv1_vlan_tci) +
                "  hv1_tp_vlan_tpid: " + Short.toUnsignedInt(hv1_tp_vlan_tpid) +
                "  hv1_padding: " + Short.toUnsignedInt(hv1_padding);
    }
}
