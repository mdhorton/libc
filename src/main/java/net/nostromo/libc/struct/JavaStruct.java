package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;

public abstract class JavaStruct {

    public void init(NativeHeapBuffer buffer, long offset) {
        buffer.setOffset(offset);
        init(buffer);
    }

    abstract void init(NativeHeapBuffer buffer);
}
