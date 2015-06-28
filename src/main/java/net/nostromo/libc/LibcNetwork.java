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

import net.nostromo.libc.struct.IfReq;
import net.nostromo.libc.struct.IfReqRn;
import net.nostromo.libc.struct.IfReqRu;

public class LibcNetwork implements LibcConstants {

    static {
        System.loadLibrary("c_jni");
    }

    public native int socket(int domain, int type, int protocol);

    public native int setsockopt(int sockfd, int level, int optname, long ptr_optval, int optlen);

    public native int ioctl(int fd, long request, long ptr_ifreq);

    public void enablePromiscMode(final int fd, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRn.Name.NAME, IfReqRu.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        ioctl(fd, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already enabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) != 0) return;

        ifReq.ifru.flags |= IFF_PROMISC;
        ioctl(fd, SIOCSIFFLAGS, ifReq.pointer());
    }

    public void disablePromiscMode(final int fd, final String ifName) {
        final IfReq ifReq = new IfReq(IfReqRn.Name.NAME, IfReqRu.Name.FLAGS);
        Struct.copyString(ifReq.ifrn.name, ifName);

        ioctl(fd, SIOCGIFFLAGS, ifReq.pointer());
        ifReq.read();

        // is it already disabled?
        if ((ifReq.ifru.flags & IFF_PROMISC) == 0) return;

        ifReq.ifru.flags &= ~IFF_PROMISC;
        ioctl(fd, SIOCSIFFLAGS, ifReq.pointer());
    }

    public static void main(String[] args) throws Exception {
        final String ifName = "enp2s0f1";
        final LibcNetwork libc = new LibcNetwork();
        final int fd = libc.socket(PF_PACKET, SOCK_RAW, ETH_P_ALL);

        libc.enablePromiscMode(fd, ifName);
        libc.disablePromiscMode(fd, ifName);
    }
}
