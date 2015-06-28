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

package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Union;

public class TPacketBdHeaderU extends Union {

    // total bytes
    public static final int SIZE = 40;

    // tpacket_bd_header_u (linux/if_packet.h)
    public TPacketHdrV1 bh1;

    public TPacketBdHeaderU(final NativeHeapBuffer buffer) {
        super(buffer);
    }

    @Override
    public void setFieldName(final FieldName fieldName) {

    }

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        bh1.read(buffer);
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        bh1.write(buffer);
    }
}
