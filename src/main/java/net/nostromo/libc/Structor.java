package net.nostromo.libc;

import net.nostromo.libc.c.sockaddr_ll;
import net.nostromo.libc.c.tpacket_req3;

public class Structor implements LibcConstants {

    public static sockaddr_ll sockaddr_ll(final String ifname, final short linkFamily, final int protocol) {
        return sockaddr_ll(ifname, linkFamily, protocol, ZERO_SHORT, ZERO_BYTE, ZERO_BYTE);
    }

    public static sockaddr_ll sockaddr_ll(final String ifname, final short linkFamily, final int protocol,
            final short hatype, final byte pkttype, final byte halen) {
        final sockaddr_ll saLink = new sockaddr_ll();

        saLink.sll_family = linkFamily;
        saLink.sll_protocol = Util.htons((short) protocol);
        saLink.sll_ifindex = LIBC.if_nametoindex(ifname);
        saLink.sll_hatype = hatype;
        saLink.sll_pkttype = pkttype;
        saLink.sll_halen = halen;
        saLink.write();

        return saLink;
    }

    public static tpacket_req3 tpacket_req3(final int blockSize, final int frameSize, final int blockCnt,
            final int timeout, final int privOffset, final int featureReq) {
        final tpacket_req3 req = new tpacket_req3();

        req.tp_block_size = blockSize;
        req.tp_frame_size = frameSize;
        req.tp_block_nr = blockCnt;
        req.tp_frame_nr = (int) (((long) blockSize * blockCnt) / frameSize);
        req.tp_retire_blk_tov = timeout;
        req.tp_sizeof_priv = privOffset;
        req.tp_feature_req_word = featureReq;
        req.write();

        return req;
    }
}
