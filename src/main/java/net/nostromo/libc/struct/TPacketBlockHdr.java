package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

import java.math.BigInteger;

public class TPacketBlockHdr extends JavaStruct {

    public static final int SIZE = 40;

    // total 40 bytes
    public int version;             // 32
    public int offset_to_priv;      // 32
    public int block_status;        // 32
    public int num_packets;         // 32
    public int offset_to_first_pkt; // 32
    public int block_len;           // 32
    public long seq_num;            // 64
    public int ts_sec;              // 32
    public int ts_un_sec;           // 32

    @Override
    void init(final NativeHeapBuffer buffer) {
        version = buffer.getInt();
        offset_to_priv = buffer.getInt();
        block_status = buffer.getInt();
        num_packets = buffer.getInt();
        offset_to_first_pkt = buffer.getInt();
        block_len = buffer.getInt();
        seq_num = buffer.getLong();
        ts_sec = buffer.getInt();
        ts_un_sec = buffer.getInt();
    }

    @Override
    public String toString() {
        return "TP_BLOCK_HDR" +
                "  version: " + Integer.toUnsignedLong(version) +
                "  offset_to_priv: " + Integer.toUnsignedLong(offset_to_priv) +
                "  block_status: " + Integer.toUnsignedLong(block_status) +
                "  num_packets: " + Integer.toUnsignedLong(num_packets) +
                "  offset_to_first_pkt: " + Integer.toUnsignedLong(offset_to_first_pkt) +
                "  block_len: " + Integer.toUnsignedLong(block_len) +
                "  seq_num: " + BigInteger.valueOf(seq_num) +
                "  ts_sec: " + Integer.toUnsignedLong(ts_sec) +
                "  ts_un_sec: " + Integer.toUnsignedLong(ts_un_sec);
    }
}
