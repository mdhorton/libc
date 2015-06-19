package net.nostromo.libc.c;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import java.util.Collections;
import java.util.List;

public class cpu_set_t extends Structure {

    public static final int CPU_SETSIZE = 1024;
    public static final int NCPUBITS = 8 * NativeLong.SIZE;

    public NativeLong[] __bits = new NativeLong[CPU_SETSIZE / NCPUBITS];

    public cpu_set_t() {
        for (int i = 0; i < __bits.length; i++) {
            __bits[i] = new NativeLong(0L);
        }
    }

    protected List<?> getFieldOrder() {
        return Collections.singletonList("__bits");
    }

    private int CPUELT(final int cpu) {
        return cpu / NCPUBITS;
    }

    private long CPUMASK(final int cpu) {
        return 1L << (cpu % NCPUBITS);
    }

    public void CPU_SET(final int cpu) {
        __bits[CPUELT(cpu)].setValue(__bits[CPUELT(cpu)].longValue() | CPUMASK(cpu));
    }
}
