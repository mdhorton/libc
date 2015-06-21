package net.nostromo.libc;

import com.sun.jna.Structure;
import net.nostromo.libc.c.sockaddr;

public class Socket implements LibcConstants {

    protected final int fd;

    public Socket(final int domain, final int type, final int protocol) {
        fd = LIBC.socket(domain, type, Util.htons((short) protocol));
    }

    public void bind(final Structure struct) {
        final sockaddr sa = new sockaddr(struct.getPointer());
        sa.read();
        LIBC.bind(fd, sa, struct.size());
    }

    public int getFd() {
        return fd;
    }
}
