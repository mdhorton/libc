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

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.struct.Struct;

// sockaddr (bits/socket.h)
public class SockAddr extends Struct {

    // total bytes
    public static final int SIZE = 16;

    public short sa_family;               // u16
    public byte[] sa_data = new byte[14]; // 8[14]

    @Override
    public void read(final NativeHeapBuffer buffer) {
        sa_family = buffer.getShort();
        buffer.getBytes(sa_data);
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        buffer.setShort(sa_family);
        buffer.setBytes(sa_data);
    }
}
