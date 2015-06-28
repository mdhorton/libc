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

package net.nostromo.libc.struct;

import net.nostromo.libc.NativeHeapBuffer;
import net.nostromo.libc.Struct;

public class IfMap extends Struct {

    // total bytes
    public static final int SIZE = 24;

    // ifmap (net/if.h)
    public long mem_start;               // u64
    public long mem_end;                 // u64
    public short base_addr;              // u16
    public byte irq;                     // u8
    public byte dma;                     // u8
    public byte port;                    // u8
    // 3 spare bytes

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        mem_start = buffer.getLong();
        mem_end = buffer.getLong();
        base_addr = buffer.getShort();
        irq = buffer.getByte();
        dma = buffer.getByte();
        port = buffer.getByte();
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        buffer.setLong(mem_start);
        buffer.setLong(mem_end);
        buffer.setShort(base_addr);
        buffer.setByte(irq);
        buffer.setByte(dma);
        buffer.setByte(port);
    }
}
