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

package net.nostromo.libc.struct.network.ifreq;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Union;

// ifreq (linux/if.h)
public class IfReqRnUnion extends Union {

    // total bytes
    public static final int SIZE = IfReq.IF_NAMESIZE;

    public enum Name implements FieldName {NAME}

    public byte[] name; // 8[IF_NAMESIZE]

    public IfReqRnUnion(final Name name) {
        setFieldName(name);
    }

    @Override
    public void setFieldName(final FieldName unionField, final boolean instantiateObjects) {
        this.fieldName = unionField;

        if (unionField == Name.NAME) name = new byte[IfReq.IF_NAMESIZE];
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        if (fieldName == Name.NAME) buffer.getBytes(name);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        if (fieldName == Name.NAME) buffer.setBytes(name);
    }
}
