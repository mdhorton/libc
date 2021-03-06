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

package net.nostromo.libc.struct.network.socket;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// sock_fprog (linux/filter.h)
public class SockFProg extends Struct {

    // total bytes
    // 16 bytes due to struct alignment of the widest scalar member
    public static final int BYTES = 16;

    public short len;       // u16
    // pointer to struct sock_filter
    public long ptr_filter; // u64

    public SockFProg() {
        super(BYTES);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        len = buffer.getShort();
        ptr_filter = buffer.getLong();
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setShort(len);
        // skip 6 bytes due to struct alignment
        buffer.setOffset(buffer.getOffset() + 6);
        buffer.setLong(ptr_filter);
    }
}
