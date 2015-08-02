package net.nostromo.libc;

public interface LibcConstants {

    // file open flags
    int O_RDONLY = LibcConstantsJni.getO_RDONLY();
    int O_WRONLY = LibcConstantsJni.getO_WRONLY();
    int O_RDWR = LibcConstantsJni.getO_RDWR();
    int O_CREAT = LibcConstantsJni.getO_CREAT();
    int O_EXCL = LibcConstantsJni.getO_EXCL();
    int O_NOCTTY = LibcConstantsJni.getO_NOCTTY();
    int O_TRUNC = LibcConstantsJni.getO_TRUNC();
    int O_APPEND = LibcConstantsJni.getO_APPEND();
    int O_NONBLOCK = LibcConstantsJni.getO_NONBLOCK();

    // file modes
    int S_IRUSR = LibcConstantsJni.getS_IRUSR();
    int S_IWUSR = LibcConstantsJni.getS_IWUSR();
    int S_IXUSR = LibcConstantsJni.getS_IXUSR();
    int S_IRWXU = S_IRUSR | S_IWUSR | S_IXUSR;
    int S_IRGRP = S_IRUSR >>> 3;
    int S_IWGRP = S_IWUSR >>> 3;
    int S_IXGRP = S_IXUSR >>> 3;
    int S_IRWXG = S_IRWXU >>> 3;
    int S_IROTH = S_IRGRP >>> 3;
    int S_IWOTH = S_IWGRP >>> 3;
    int S_IXOTH = S_IXGRP >>> 3;
    int S_IRWXO = S_IRWXG >>> 3;

    // file seek
    int SEEK_SET = LibcConstantsJni.getSEEK_SET();
    int SEEK_CUR = LibcConstantsJni.getSEEK_CUR();
    int SEEK_END = LibcConstantsJni.getSEEK_END();
    int SEEK_DATA = LibcConstantsJni.getSEEK_DATA();
    int SEEK_HOLE = LibcConstantsJni.getSEEK_HOLE();

    // poll
    int POLLIN = LibcConstantsJni.getPOLLIN();
    int POLLERR = LibcConstantsJni.getPOLLERR();

    // mmap
    int PROT_READ = LibcConstantsJni.getPROT_READ();
    int PROT_WRITE = LibcConstantsJni.getPROT_WRITE();

    int MAP_SHARED = LibcConstantsJni.getMAP_SHARED();
    int MAP_LOCKED = LibcConstantsJni.getMAP_LOCKED();
    int MAP_NORESERVE = LibcConstantsJni.getMAP_NORESERVE();
    int MAP_POPULATE = LibcConstantsJni.getMAP_POPULATE();

    // ioctl
    int SIOCGIFFLAGS = LibcConstantsJni.getSIOCGIFFLAGS();
    int SIOCSIFFLAGS =  LibcConstantsJni.getSIOCSIFFLAGS();
    int SIOCGIFMTU = LibcConstantsJni.getSIOCGIFMTU();
    int SIOCSIFMTU = LibcConstantsJni.getSIOCSIFMTU();
    int SIOCGIFINDEX = LibcConstantsJni.getSIOCGIFINDEX();

    int SIOCSHWTSTAMP = LibcConstantsJni.getSIOCSHWTSTAMP();
    int SIOCGHWTSTAMP = LibcConstantsJni.getSIOCGHWTSTAMP();

    int IFF_PROMISC = LibcConstantsJni.getIFF_PROMISC();

    int SO_RCVBUF = 8;

    int PF_PACKET = 17;
    int SOCK_RAW = 3;

    int ETH_HLEN = 14;

    // these need to be constants for switch() and case statements
    byte IPPROTO_IP = 0;
    byte IPPROTO_ICMP = 1;
    byte IPPROTO_IGMP = 2;
    byte IPPROTO_TCP = 6;
    byte IPPROTO_UDP = 17;
    byte IPPROTO_MTP = 92;
    int ETH_P_ALL = 0x0003;
    int ETH_P_IP = 0x0800;
    int ETH_P_IPV6 = 0x86DD;
    int ETH_P_ARP = 0x0806;
    int ETH_P_RARP = 0x8035;

    int SOL_SOCKET = 1;
    int SOL_PACKET = 263;

    // packet filtering
    int SO_ATTACH_FILTER = 26;
    int SO_DETACH_FILTER = 27;

    int TPACKET_V1 = 0;
    int TPACKET_V2 = 1;
    int TPACKET_V3 = 2;

    int TPACKET2_HDRLEN = LibcConstantsJni.getTPACKET2_HDRLEN();
    int TPACKET3_HDRLEN = LibcConstantsJni.getTPACKET3_HDRLEN();

    int PACKET_ADD_MEMBERSHIP = LibcConstantsJni.getPACKET_ADD_MEMBERSHIP();
    int PACKET_DROP_MEMBERSHIP = LibcConstantsJni.getPACKET_DROP_MEMBERSHIP();
    int PACKET_RX_RING = 5;
    int PACKET_COPY_THRESH = 7;
    int PACKET_VERSION = 10;
    int PACKET_TX_RING = 13;
    int PACKET_LOSS = 14;
    int PACKET_FANOUT = 18;
    int PACKET_QDISC_BYPASS = 20;

    int PACKET_FANOUT_HASH = 0;
    int PACKET_FANOUT_LB = 1;
    int PACKET_FANOUT_CPU = 2;
    int PACKET_FANOUT_ROLLOVER = 3;
    int PACKET_FANOUT_RND = 4;
    int PACKET_FANOUT_QM = 5;
    int PACKET_FANOUT_FLAG_ROLLOVER = 0x1000;
    int PACKET_FANOUT_FLAG_DEFRAG = 0x8000;

    int PACKET_MR_MULTICAST = LibcConstantsJni.getPACKET_MR_MULTICAST();
    int PACKET_MR_PROMISC = LibcConstantsJni.getPACKET_MR_PROMISC();
    int PACKET_MR_ALLMULTI = LibcConstantsJni.getPACKET_MR_ALLMULTI();
    int PACKET_MR_UNICAST = LibcConstantsJni.getPACKET_MR_UNICAST();

    int TP_STATUS_KERNEL = 0;
    int TP_STATUS_USER = 1;
    int TP_FT_REQ_FILL_RXHASH = 0x1;

    int CLOCK_REALTIME = LibcConstantsJni.getCLOCK_REALTIME();
    int CLOCK_MONOTONIC = LibcConstantsJni.getCLOCK_MONOTONIC();
    int CLOCK_PROCESS_CPUTIME_ID = LibcConstantsJni.getCLOCK_PROCESS_CPUTIME_ID();
    int CLOCK_THREAD_CPUTIME_ID = LibcConstantsJni.getCLOCK_THREAD_CPUTIME_ID();
    int CLOCK_MONOTONIC_RAW = LibcConstantsJni.getCLOCK_MONOTONIC_RAW();
    int CLOCK_REALTIME_COARSE = LibcConstantsJni.getCLOCK_REALTIME_COARSE();
    int CLOCK_MONOTONIC_COARSE = LibcConstantsJni.getCLOCK_MONOTONIC_COARSE();
    int CLOCK_BOOTTIME = LibcConstantsJni.getCLOCK_BOOTTIME();
    int CLOCK_REALTIME_ALARM = LibcConstantsJni.getCLOCK_REALTIME_ALARM();
    int CLOCK_BOOTTIME_ALARM = LibcConstantsJni.getCLOCK_BOOTTIME_ALARM();
}
