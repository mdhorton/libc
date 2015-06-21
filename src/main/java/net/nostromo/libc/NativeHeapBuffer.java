package net.nostromo.libc;

import sun.misc.Unsafe;

public class NativeHeapBuffer {

    protected static final Unsafe UNSAFE = TheUnsafe.UNSAFE;
    protected static final long BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);

    protected final byte[] buffer;
    protected final int bufferSize;
    protected final long pointerAddress;

    protected long bufferOffset = BYTE_ARRAY_OFFSET;

    public NativeHeapBuffer(final int bufferSize, final long pointerAddress) {
        this.buffer = new byte[bufferSize];
        this.bufferSize = bufferSize;
        this.pointerAddress = pointerAddress;
    }

    public void setOffset(final long offset) {
        bufferOffset = BYTE_ARRAY_OFFSET + offset;
    }

    public void refill(final long offset) {
        refill(offset, bufferSize);
    }

    public void refill(final long offset, final long length) {
        UNSAFE.copyMemory(null, pointerAddress + offset, buffer, BYTE_ARRAY_OFFSET, length);
        bufferOffset = BYTE_ARRAY_OFFSET;
    }

    public void getBytes(final byte[] bytes) {
        System.arraycopy(buffer, (int) (bufferOffset - BYTE_ARRAY_OFFSET), bytes, 0, bytes.length);
        bufferOffset += bytes.length;
    }

    public byte getByte() {
        return buffer[(int) (bufferOffset++ - BYTE_ARRAY_OFFSET)];
    }

    public byte getByte(final int offset) {
        return buffer[offset];
    }

    public short getShort() {
        final short val = UNSAFE.getShort(buffer, bufferOffset);
        bufferOffset += 2;
        return val;
    }

    public short getShort(final int offset) {
        return UNSAFE.getShort(buffer, BYTE_ARRAY_OFFSET + offset);
    }

    public short getNetworkShort() {
        if (Util.BIG_ENDIAN) return getShort();
        else return Short.reverseBytes(getShort());
    }

    public int getInt() {
        final int val = UNSAFE.getInt(buffer, bufferOffset);
        bufferOffset += 4;
        return val;
    }

    public int getInt(final int offset) {
        return UNSAFE.getInt(buffer, BYTE_ARRAY_OFFSET + offset);
    }

    public int getNetworkInt() {
        if (Util.BIG_ENDIAN) return getInt();
        else return Integer.reverseBytes(getInt());
    }

    public long getLong() {
        final long val = UNSAFE.getLong(buffer, bufferOffset);
        bufferOffset += 8;
        return val;
    }

    public long getLong(final int offset) {
        return UNSAFE.getLong(buffer, BYTE_ARRAY_OFFSET + offset);
    }

    public long getNetworkLong() {
        if (Util.BIG_ENDIAN) return getLong();
        else return Long.reverseBytes(getLong());
    }
}
