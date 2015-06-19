package net.nostromo.libc.model;

public class TPacketBlockHdr {
    public int version;
    public int offsetToPriv;
    public int blockStatus;
    public int numPackets;
    public int offsetToFirstPkt;
    public int blockLen;
    public long seqNum;
    public int tsSec;
    public int tsUNSec;
}
