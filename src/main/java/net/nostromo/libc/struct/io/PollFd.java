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

package net.nostromo.libc.struct.io;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// pollfd (sys/poll.h)
public class PollFd extends Struct {

    // total bytes
    public static final int SIZE = 8;

    public int fd;        // 32
    public short events;  // 16
    public short revents; // 16

    public PollFd() {
        super(SIZE);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        fd = buffer.getInt();
        events = buffer.getShort();
        revents = buffer.getShort();
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setInt(fd);
        buffer.setShort(events);
        buffer.setShort(revents);
    }
}
