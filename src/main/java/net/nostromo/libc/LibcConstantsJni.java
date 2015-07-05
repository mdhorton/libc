package net.nostromo.libc;

public class LibcConstantsJni {

    public static native int getO_RDONLY();

    public static native int getO_WRONLY();

    public static native int getO_RDWR();

    public static native int getO_CREAT();

    public static native int getO_EXCL();

    public static native int getO_NOCTTY();

    public static native int getO_TRUNC();

    public static native int getO_APPEND();

    public static native int getO_NONBLOCK();

    public static native int getS_IRUSR();

    public static native int getS_IWUSR();

    public static native int getS_IXUSR();

    public static native int getSEEK_SET();

    public static native int getSEEK_CUR();

    public static native int getSEEK_END();

    public static native int getSEEK_DATA();

    public static native int getSEEK_HOLE();

    public static native int getPOLLIN();

    public static native int getPOLLERR();

    public static native int getPROT_READ();

    public static native int getPROT_WRITE();

    public static native int getMAP_SHARED();

    public static native int getMAP_LOCKED();

    public static native int getMAP_NORESERVE();

    public static native int getPACKET_ADD_MEMBERSHIP();

    public static native int getPACKET_DROP_MEMBERSHIP();

    public static native int getPACKET_MR_MULTICAST();

    public static native int getPACKET_MR_PROMISC();

    public static native int getPACKET_MR_ALLMULTI();

    public static native int getPACKET_MR_UNICAST();

    public static native int getCLOCK_REALTIME();

    public static native int getCLOCK_MONOTONIC();

    public static native int getCLOCK_PROCESS_CPUTIME_ID();

    public static native int getCLOCK_THREAD_CPUTIME_ID();

    public static native int getCLOCK_MONOTONIC_RAW();

    public static native int getCLOCK_REALTIME_COARSE();

    public static native int getCLOCK_MONOTONIC_COARSE();

    public static native int getCLOCK_BOOTTIME();

    public static native int getCLOCK_REALTIME_ALARM();

    public static native int getCLOCK_BOOTTIME_ALARM();
}
