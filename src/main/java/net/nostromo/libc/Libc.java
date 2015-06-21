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

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import net.nostromo.libc.c.cpu_set_t;
import net.nostromo.libc.c.pollfd;
import net.nostromo.libc.c.sockaddr;

public interface Libc extends Library {

    int socket(int domain, int type, int protocol) throws LastErrorException;

    int setsockopt(int fd, int level, int optionName, Pointer optionValue, int optionLen) throws LastErrorException;

    int bind(int fd, sockaddr address, int addressLen) throws LastErrorException;

    int if_nametoindex(String ifname) throws LastErrorException;

    int open(String pathName, int flags) throws LastErrorException;

    int open(String pathName, int flags, int mode) throws LastErrorException;

    long lseek(int fd, long offset, int whence) throws LastErrorException;

    int truncate(String path, long length) throws LastErrorException;

    int ftruncate(int fd, long length) throws LastErrorException;

    long write(int fd, Pointer buf, long count) throws LastErrorException;

    long read(int fd, Pointer buf, long count) throws LastErrorException;

    Pointer mmap(Pointer addr, long length, int prot, int flags, int fd, long offset) throws LastErrorException;

    int poll(pollfd fds, long nfds, int timeout) throws LastErrorException;

    int sched_setaffinity(int pid, int cpusetsize, cpu_set_t cpuset) throws LastErrorException;

    int getpagesize() throws LastErrorException;

    int getpid() throws LastErrorException;

    long syscall(long sysno) throws LastErrorException;

    String strerror(int errno) throws LastErrorException;
}
