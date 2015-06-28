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

package net.nostromo.libc.struct.network.tpacket.block;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Union;

// tpacket_bd_header_u (linux/if_packet.h)
public class TPacketBdHeaderU extends Union {

    // total bytes
    public static final int SIZE = 40;

    public enum Name implements FieldName {BH1}

    public TPacketHdrV1 bh1;

    public TPacketBdHeaderU(final Name name) {
        setFieldName(name);
    }

    @Override
    public void setFieldName(final FieldName fieldName, final boolean instantiateObjects) {
        this.fieldName = fieldName;

        if (fieldName == Name.BH1) bh1 = new TPacketHdrV1(instantiateObjects);
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {
        if (fieldName == Name.BH1) bh1.read(buffer);
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        if (fieldName == Name.BH1) bh1.write(buffer);
    }
}
