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

package net.nostromo.libc.io;

public interface LibcIoConstants {

    // poll
    int POLLIN = 0x001;
    int POLLERR = 0x008;

    // mmap
    int PROT_READ = 0x1;
    int PROT_WRITE = 0x2;

    int MAP_SHARED = 0x01;
    int MAP_LOCKED = 0x02000;
    int MAP_NORESERVE = 0x04000;

    // ioctl
    int SIOCGIFFLAGS = 0x8913;
    int SIOCSIFFLAGS = 0x8914;
    int SIOCGIFMTU = 0x8921;
    int SIOCGIFINDEX = 0x8933;

    int IFF_PROMISC = 0x100;
}
