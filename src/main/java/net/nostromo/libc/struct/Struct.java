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

import net.nostromo.libc.OffHeapBuffer;

public abstract class Struct {

    // TODO: handle size more accurately, replace BYTES with size()

    protected OffHeapBuffer buffer;
    protected boolean instantiateObjects;

    public Struct() {
        this(null, true);
    }

    public Struct(final long size) {
        this(OffHeapBuffer.allocate(size), true);
    }

    public Struct(final OffHeapBuffer buffer) {
        this(buffer, true);
    }

    public Struct(final boolean instantiateObjects) {
        this(null, instantiateObjects);
    }

    public Struct(final OffHeapBuffer buffer, final boolean instantiateObjects) {
        this.buffer = buffer;
        this.instantiateObjects = instantiateObjects;
        if (instantiateObjects) instantiateObjects();
    }

    public abstract void read(OffHeapBuffer buffer);

    public abstract void write(OffHeapBuffer buffer);

    public void instantiateObjects() {}

    public void read() {
        read(0);
    }

    public void write() {
        write(0);
    }

    public void read(final long offset) {
        read(buffer, offset);
    }

    public void write(final long offset) {
        write(buffer, offset);
    }

    public void read(final OffHeapBuffer buffer, final long offset) {
        buffer.setOffset(offset);
        read(buffer);
    }

    public void write(final OffHeapBuffer buffer, final long offset) {
        buffer.setOffset(offset);
        write(buffer);
    }

    public long pointer() {
        write();
        return bufferPointer();
    }

    public long bufferPointer() {
        return buffer.pointer();
    }

    public static void copyString(final byte[] bytes, final String str) {
        final byte[] strBytes = str.getBytes();
        final int len = Math.min(strBytes.length, bytes.length - 1);
        System.arraycopy(strBytes, 0, bytes, 0, len);
    }
}
