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
