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

import sun.misc.Unsafe;

public class OffHeapBuffer {

    protected static final Unsafe unsafe = TheUnsafe.unsafe;

    protected final long pointer;
    protected final boolean allocated;

    protected long offset;

    private OffHeapBuffer(final long pointer, final boolean allocated) {
        this.pointer = pointer;
        this.allocated = allocated;
    }

    public static OffHeapBuffer allocate(final long size) {
        final long pointer = unsafe.allocateMemory(size);
        return new OffHeapBuffer(pointer, true);
    }

    public static OffHeapBuffer attach(final long pointer) {
        return new OffHeapBuffer(pointer, false);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            freeMemory();
        }
        finally {
            super.finalize();
        }
    }

    public void freeMemory() {
        if (allocated) unsafe.freeMemory(pointer);
    }

    public long pointer() {
        return pointer;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(final long offset) {
        this.offset = offset;
    }

    // BYTE[]

    public void getBytes(final byte[] bytes) {
        getBytes(offset, bytes, bytes.length);
        offset += bytes.length;
    }

    public void setBytes(final byte[] bytes) {
        setBytes(offset, bytes);
        offset += bytes.length;
    }

    public void getBytes(final byte[] bytes, final int length) {
        getBytes(offset, bytes, length);
        offset += length;
    }

    public void setBytes(final long offset, final byte[] bytes) {
        unsafe.copyMemory(bytes, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, pointer + offset,
                bytes.length);
    }

    public void getBytes(final long offset, final byte[] bytes, final int length) {
        unsafe.copyMemory(null, pointer + offset, bytes, Unsafe.ARRAY_BYTE_BASE_OFFSET, length);
    }

    // BYTE

    public byte getByte() {
        return getByte(offset++);
    }

    public void setByte(final byte b) {
        setByte(offset++, b);
    }

    public byte getByte(final long offset) {
        return unsafe.getByte(pointer + offset);
    }

    public void setByte(final long offset, final byte b) {
        unsafe.putByte(pointer + offset, b);
    }

    // SHORT

    public short getShort() {
        final short val = getShort(offset);
        offset += 2;
        return val;
    }

    public void setShort(final short s) {
        setShort(offset, s);
        offset += 2;
    }

    public short getShort(final long offset) {
        return unsafe.getShort(pointer + offset);
    }

    public void setShort(final long offset, final short s) {
        unsafe.putShort(pointer + offset, s);
    }

    public short getNetworkShort() {
        if (Util.BIG_ENDIAN) return getShort();
        else return Short.reverseBytes(getShort());
    }

    public void setNetworkShort(final short s) {
        if (Util.BIG_ENDIAN) setShort(s);
        else setShort(Short.reverseBytes(s));
    }

    // INTEGER

    public int getInt() {
        final int val = getInt(offset);
        offset += 4;
        return val;
    }

    public void setInt(final int i) {
        setInt(offset, i);
        offset += 4;
    }

    public int getInt(final long offset) {
        return unsafe.getInt(pointer + offset);
    }

    public void setInt(final long offset, final int i) {
        unsafe.putInt(pointer + offset, i);
    }

    public int getNetworkInt() {
        if (Util.BIG_ENDIAN) return getInt();
        else return Integer.reverseBytes(getInt());
    }

    public void setNetworkInt(final int i) {
        if (Util.BIG_ENDIAN) setInt(i);
        else setInt(Integer.reverseBytes(i));
    }

    // LONG

    public long getLong() {
        final long val = getLong(offset);
        offset += 8;
        return val;
    }

    public void setLong(final long l) {
        setLong(offset, l);
        offset += 8;
    }

    public long getLong(final long offset) {
        return unsafe.getLong(pointer + offset);
    }

    public void setLong(final long offset, final long l) {
        unsafe.putLong(pointer + offset, l);
    }

    public long getNetworkLong() {
        if (Util.BIG_ENDIAN) return getLong();
        else return Long.reverseBytes(getLong());
    }

    public void setNetworkLong(final long l) {
        if (Util.BIG_ENDIAN) setLong(l);
        else setLong(Long.reverseBytes(l));
    }

    // LONG[]
    public void getLongs(final long[] longs) {
        for (int idx = 0; idx < longs.length; idx++) {
            longs[idx] = getLong();
        }
    }

    public void setLongs(final long[] longs) {
        for (final long aLong : longs) {
            setLong(aLong);
        }
    }
}
