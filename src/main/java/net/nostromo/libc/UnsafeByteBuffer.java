package net.nostromo.libc;

import sun.misc.Unsafe;

public class UnsafeByteBuffer {

    protected static final Unsafe UNSAFE = TheUnsafe.UNSAFE;
    protected static final long byteArrayOffset = UNSAFE.arrayBaseOffset(byte[].class);

    protected final byte[] buffer;
    protected final int bufferSize;

    private int idx;

    public UnsafeByteBuffer(final int bufferSize) {
        this.buffer = new byte[bufferSize];
        this.bufferSize = bufferSize;
    }

    public void copyMemoryFromOffHeap(final long srcOffset) {
        UNSAFE.copyMemory(null, srcOffset, buffer, byteArrayOffset, bufferSize);
    }

    public byte getByte() {
        return buffer[idx++];
    }

    public byte getByte(final int idx) {
        return buffer[idx];
    }

    public int getInt() {
        final int i = getInt(idx);
        idx += 4;
        return i;
    }

    public int getInt(final int idx) {
        return (((buffer[idx + 3]) << 24) |
                ((buffer[idx + 2] & 0xff) << 16) |
                ((buffer[idx + 1] & 0xff) << 8) |
                ((buffer[idx] & 0xff)));
    }

}
