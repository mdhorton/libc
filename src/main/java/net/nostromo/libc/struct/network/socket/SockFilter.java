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

// sock_filter (linux/filter.h)
public class SockFilter extends Struct {

    private static final int BYTES = 8;

    private final Object[][] filter;

    public SockFilter(final Object[][] filter) {
        super(BYTES * filter.length);
        this.filter = filter;
    }

    @Override
    public void read(final OffHeapBuffer buffer) {}

    @Override
    public void write(final OffHeapBuffer buffer) {
        // short code  u16
        // byte jt     u8
        // byte jk     u8
        // int k       u32
        for (final Object[] obj : filter) {
            buffer.setShort(((Integer) obj[0]).shortValue());
            buffer.setByte(((Integer) obj[1]).byteValue());
            buffer.setByte(((Integer) obj[2]).byteValue());
            buffer.setInt(((Integer) obj[3]));
        }
    }
}
