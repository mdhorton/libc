package net.nostromo.libc.struct.c;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class ifmap extends Structure {

    public long mem_start;
    public long mem_end;
    public short base_addr;
    public byte irq;
    public byte dma;
    public byte port;
    // 3 spare bytes

    public ifmap() { }

    public ifmap(final Pointer p) {
        super(p);
    }

    protected List<?> getFieldOrder() {
        return Arrays.asList("mem_start", "mem_end", "base_addr", "irq", "dma", "port");
    }
}
