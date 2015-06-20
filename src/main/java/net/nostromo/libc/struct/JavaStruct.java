package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

import java.nio.ByteOrder;

public abstract class JavaStruct {

    protected static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);

    public void init(NativeHeapBuffer buffer, long offset) {
        buffer.setOffset(offset);
        init(buffer);
    }

    abstract void init(NativeHeapBuffer buffer);
}
