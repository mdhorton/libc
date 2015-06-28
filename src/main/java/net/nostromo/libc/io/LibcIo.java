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

import net.nostromo.libc.Libc;
import net.nostromo.libc.struct.Struct;
import net.nostromo.libc.struct.network.ifreq.IfReq;
import net.nostromo.libc.struct.network.ifreq.IfReqRnUnion;
import net.nostromo.libc.struct.network.ifreq.IfReqRuUnion;

public class LibcIo extends Libc {

    public native int ioctl(int fd, long request, long ptr_ifreq);

    public native long write(int fd, long ptr_buf, long count);

    public native long read(int fd, long ptr_buf, long count);

    public native long mmap(long ptr_addr, long length, int prot, int flags, int fd, long offset);

    public native int poll(long ptr_fds, long nfds, int timeout);

    public void enablePromiscMode(final int fd, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        ioctl(fd, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already enabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) != 0) return;

        ifReq.ifru.flags |= IFF_PROMISC;
        ioctl(fd, SIOCSIFFLAGS, ifReq.pointer());
    }

    public void disablePromiscMode(final int fd, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRnUnion.Name.NAME, IfReqRuUnion.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        ioctl(fd, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already disabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) == 0) return;

        ifReq.ifru.flags &= ~IFF_PROMISC;
        ioctl(fd, SIOCSIFFLAGS, ifReq.pointer());
    }
}
