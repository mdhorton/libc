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

package net.nostromo.libc;

public abstract class Struct {

    protected NativeHeapBuffer buffer;

    public Struct() {}

    public Struct(final int size) {
        buffer = new NativeHeapBuffer(size);
    }

    public Struct(NativeHeapBuffer buffer) {
        this.buffer = buffer;
    }

    protected abstract void read(NativeHeapBuffer buffer);

    protected abstract void write(NativeHeapBuffer buffer);

    public void read() {
        buffer.read(0);
        read(0);
    }

    public void write() {
        write(0);
        buffer.write(0);
    }

    public void read(final int offset) {
        read(buffer, offset);
    }

    public void write(final int offset) {
        write(buffer, offset);
    }

    public void read(final NativeHeapBuffer buffer, final int offset) {
        buffer.setOffset(offset);
        read(buffer);
    }

    public void write(final NativeHeapBuffer buffer, final int offset) {
        buffer.setOffset(offset);
        write(buffer);
    }

    public long pointer() {
        write();
        return buffer.getPointer();
    }

    public static void copyString(final byte[] bytes, final String str) {
        final byte[] strBytes = str.getBytes();
        final int len = Math.min(strBytes.length, bytes.length - 1);
        System.arraycopy(strBytes, 0, bytes, 0, len);
    }
}
