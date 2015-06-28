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

package net.nostromo.libc.struct.system;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Struct;

// cpu_set_t (bits/sched.h)
public class CpuSetT extends Struct {

    public static final int CPU_SETSIZE = 1024;
    public static final int NCPUBITS = Long.SIZE;
    public static final int ARRAY_SIZE = CPU_SETSIZE / NCPUBITS;

    // total bytes
    public static final int SIZE = ARRAY_SIZE * Long.BYTES;

    public long[] bits;

    public CpuSetT() {
        super(SIZE);
    }

    @Override
    public void instantiateObjects() {
        bits = new long[ARRAY_SIZE];
    }

    @Override
    public void read(final NativeHeapBuffer buffer) {
        buffer.getLongs(bits);
    }

    @Override
    public void write(final NativeHeapBuffer buffer) {
        buffer.setLongs(bits);
    }

    private int CPUELT(final int cpu) {
        return cpu / NCPUBITS;
    }

    private long CPUMASK(final int cpu) {
        return 1L << (cpu % NCPUBITS);
    }

    public void CPU_SET(final int cpu) {
        bits[CPUELT(cpu)] = bits[CPUELT(cpu)] | CPUMASK(cpu);
    }
}
