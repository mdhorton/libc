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

import net.nostromo.libc.TheUnsafe;

public class LongRef {

    private final long pointer;

    public LongRef(final long value) {
        pointer = TheUnsafe.unsafe.allocateMemory(Long.BYTES);
        TheUnsafe.unsafe.putLong(pointer, value);
    }

    public long pointer() {
        return pointer;
    }

    public long getValue() {
        return TheUnsafe.unsafe.getLong(pointer);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            TheUnsafe.unsafe.freeMemory(pointer);
        }
        finally {
            super.finalize();
        }
    }
}
