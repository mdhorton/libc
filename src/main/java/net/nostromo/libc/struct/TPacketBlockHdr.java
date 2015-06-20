package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public class TPacketBlockHdr extends JavaStruct {

    public int version;          // u32
    public int offsetToPriv;     // u32
    public int blockStatus;      // u32
    public int numPackets;       // u32
    public int offsetToFirstPkt; // u32
    public int blockLen;         // u32
    public long seqNum;          // u64
    public int tsSec;            // u32
    public int tsUorNSec;        // u32

    @Override
    void init(final NativeHeapBuffer buffer) {
        version = buffer.getInt();
        offsetToPriv = buffer.getInt();
        blockStatus = buffer.getInt();
        numPackets = buffer.getInt();
        offsetToFirstPkt = buffer.getInt();
        blockLen = buffer.getInt();
        seqNum = buffer.getLong();
        tsSec = buffer.getInt();
        tsUorNSec = buffer.getInt();
    }
}
