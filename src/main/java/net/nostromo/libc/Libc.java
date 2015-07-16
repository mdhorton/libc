/*
 * Copyright (c) 2015 Mark D. Horton
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABIL-
 * ITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.nostromo.libc;

public class Libc {

    public static final Libc libc = new Libc();

    private Libc() {}

    static {
        System.loadLibrary("c_jni");
    }

    public native int open(String pathName, int flags);

    public native int open(String pathName, int flags, int mode);

    public native int close(int fd);

    public native long lseek(int fd, long offset, int whence);

    public native int truncate(String pathName, long length);

    public native int ftruncate(int fd, long length);

    public native int ioctl(int fd, long request, long ptr_argp);

    public native long read(int fd, long ptr_buf, long count);

    public native long write(int fd, long ptr_buf, long count);

    public native long mmap(long ptr_addr, long length, int prot, int flags, int fd, long offset);

    public native int munmap(long ptr_addr, long length);

    public native int poll(long ptr_fds, long nfds, int timeout);

    public native int epoll_create(int size);

    public native int epoll_ctl(int epfd, int op, int fd, long ptr_event);

    public native int epoll_wait(int epfd, long ptr_events, int maxevents, int timeout);

    public native int socket(int domain, int type, int protocol);

    public native int setsockopt(int sock, int level, int optname, long ptr_optval, int optlen);

    public native int getsockopt(int sock, int level, int optname, long ptr_optval,
            long ptr_optlen);

    public native int bind(int sock, long ptr_address, int address_len);

    public native int sched_setaffinity(int pid, long cpusetsize, long ptr_mask);

    public native int getpagesize();

    public native int getpid();

    public native int clock_gettime(int clk_id, long ptr_timespec);

    public native long rdtscp();

    public native long syscall(long sysno);

    // this does nothing, its for timing JNI call overhead
    public native void noop();
}
