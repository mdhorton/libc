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

package net.nostromo.libc.struct.system;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// timespec (time.h)
public class Timespec extends Struct {

    // total bytes
    public static final int BYTES = 16;

    public long tv_sec;  // 64
    public long tv_nsec; // 64

    public Timespec() {
        super(BYTES);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        tv_sec = buffer.getLong();
        tv_nsec = buffer.getLong();
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        buffer.setLong(tv_sec);
        buffer.setLong(tv_nsec);
    }
}
