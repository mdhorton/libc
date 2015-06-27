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

public class NativeHeapBuffer {

    protected static final Unsafe unsafe = TheUnsafe.unsafe;

    protected final byte[] buffer;
    protected final int bufferSize;
    protected final long nativePointer;

    protected int bufferOffset;

    public NativeHeapBuffer(final int bufferSize, final long nativePointer) {
        this.buffer = new byte[bufferSize];
        this.bufferSize = bufferSize;
        this.nativePointer = nativePointer;
    }

    public void setOffset(final int offset) {
        bufferOffset = offset;
    }

    public void read(final long offset) {
        read(offset, bufferSize);
    }

    public void read(final long offset, final long length) {
        unsafe.copyMemory(null, nativePointer + offset, buffer, Unsafe.ARRAY_BYTE_BASE_OFFSET, length);
    }

    public void write(final long offset) {
        write(offset, bufferSize);
    }

    public void write(final long offset, final long length) {
        unsafe.copyMemory(buffer, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, nativePointer + offset, length);
    }

    // BYTE[]

    public void getBytes(final byte[] bytes) {
        getBytes(bufferOffset, bytes);
        bufferOffset += bytes.length;
    }

    public void setBytes(final byte[] bytes) {
        setBytes(bufferOffset, bytes);
        bufferOffset += bytes.length;
    }

    public void getBytes(final int offset, final byte[] bytes) {
        System.arraycopy(buffer, offset, bytes, 0, bytes.length);
    }

    public void setBytes(final int offset, final byte[] bytes) {
        System.arraycopy(bytes, 0, buffer, offset, bytes.length);
    }

    // BYTE

    public byte getByte() {
        final byte b = getByte(bufferOffset);
        bufferOffset++;
        return b;
    }

    public void setByte(final byte b) {
        setByte(bufferOffset, b);
        bufferOffset++;
    }

    public byte getByte(final int offset) {
        return buffer[offset];
    }

    public void setByte(final int offset, final byte b) {
        buffer[offset] = b;
    }

    // SHORT

    public short getShort() {
        final short val = getShort(bufferOffset);
        bufferOffset += 2;
        return val;
    }

    public void setShort(final short s) {
        setShort(bufferOffset, s);
        bufferOffset += 2;
    }

    public short getShort(final int offset) {
        return unsafe.getShort(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public void setShort(final int offset, final short s) {
        unsafe.putShort(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET, s);
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
        final int val = getInt(bufferOffset);
        bufferOffset += 4;
        return val;
    }

    public void setInt(final int i) {
        setInt(bufferOffset, i);
        bufferOffset += 4;
    }

    public int getInt(final int offset) {
        return unsafe.getInt(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public void setInt(final int offset, final int i) {
        unsafe.putInt(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET, i);
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
        final long val = getLong(bufferOffset);
        bufferOffset += 8;
        return val;
    }

    public void setLong(final long l) {
        setLong(bufferOffset, l);
        bufferOffset += 8;
    }

    public long getLong(final int offset) {
        return unsafe.getLong(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET);
    }

    public void setLong(final int offset, final long l) {
        unsafe.putLong(buffer, (long) offset + Unsafe.ARRAY_BYTE_BASE_OFFSET, l);
    }

    public long getNetworkLong() {
        if (Util.BIG_ENDIAN) return getLong();
        else return Long.reverseBytes(getLong());
    }

    public void setNetworkLong(final long l) {
        if (Util.BIG_ENDIAN) setLong(l);
        else setLong(Long.reverseBytes(l));
    }
}
