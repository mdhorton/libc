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

import com.sun.jna.Structure;
import net.nostromo.libc.struct.c.ifreq;
import net.nostromo.libc.struct.c.sockaddr;

public class Socket implements LibcConstants {

    protected final int fd;

    public Socket(final int domain, final int type, final int protocol) {
        fd = libc.socket(domain, type, Util.htons((short) protocol));
    }

    public void bind(final Structure struct) {
        final sockaddr sa = new sockaddr(struct.getPointer());
        sa.read();
        libc.bind(fd, sa, struct.size());
    }

    public void enablePromiscuousMode(final String ifname) {
        final byte[] src = ifname.getBytes();
        final byte[] dst = new byte[16];
        System.arraycopy(src, 0, dst, 0, src.length);

        final ifreq ifreq = new ifreq();
        ifreq.ifr_ifrn = new ifreq.ifr_ifrn_union(dst);

        libc.ioctl(fd, SIOCGIFFLAGS, ifreq);

        System.out.println(ifreq.ifr_ifru.ifru_mtu);

        // is it already enabled?
        if ((ifreq.ifr_ifru.ifru_flags & IFF_PROMISC) != 0) {
            return;
        }

        System.out.println(ifreq.ifr_ifru.ifru_flags & IFF_PROMISC);

        ifreq.ifr_ifru.ifru_flags |= IFF_PROMISC;
        libc.ioctl(fd, SIOCSIFFLAGS, ifreq);
    }

    public void disablePromiscuousMode(final String ifname) {
        final byte[] src = ifname.getBytes();
        final byte[] dst = new byte[16];
        System.arraycopy(src, 0, dst, 0, src.length);

        final ifreq ifreq = new ifreq();
        ifreq.ifr_ifrn = new ifreq.ifr_ifrn_union(dst);

        libc.ioctl(fd, SIOCGIFFLAGS, ifreq);

        System.out.println(ifreq.ifr_ifru.ifru_flags & IFF_PROMISC);

        // is it already disabled?
        if ((ifreq.ifr_ifru.ifru_flags & IFF_PROMISC) == 0) {
            return;
        }

        ifreq.ifr_ifru.ifru_flags &= ~IFF_PROMISC;
        libc.ioctl(fd, SIOCSIFFLAGS, ifreq);
    }

    public int getFd() {
        return fd;
    }
}
