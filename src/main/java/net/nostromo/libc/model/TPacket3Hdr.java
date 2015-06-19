package net.nostromo.libc.model;

public class TPacket3Hdr {

    public int tp_next_offset;
    public int tp_sec;
    public int tp_nsec;
    public int tp_snaplen;
    public int tp_len;
    public int tp_status;
    public short tp_mac;
    public short tp_net;

    // hv1
    public int hv1_rxhash;
    public int hv1_vlan_tci;
    public short hv1_tp_vlan_tpid;
    public short hv1_padding;

    public byte[] tp_padding = new byte[8];
}
