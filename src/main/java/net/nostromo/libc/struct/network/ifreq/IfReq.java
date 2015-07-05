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

package net.nostromo.libc.struct.network.ifreq;

import net.nostromo.libc.OffHeapBuffer;
import net.nostromo.libc.struct.Struct;

// ifreq (linux/if.h)
public class IfReq extends Struct {

    // total bytes
    public static final int SIZE = IfReqRnUnion.SIZE + IfReqRuUnion.SIZE;

    public static final int IF_NAMESIZE = 16;

    public IfReqRnUnion ifrn;
    public IfReqRuUnion ifru;

    public IfReq() {
        super(SIZE);
    }

    public IfReq(final IfReqRnUnion.Name rnName, final IfReqRuUnion.Name ruName) {
        this();
        ifrn = new IfReqRnUnion(rnName);
        ifru = new IfReqRuUnion(ruName);
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        ifrn.read(buffer);
        ifru.read(buffer);
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        ifrn.write(buffer);
        ifru.write(buffer);
    }
}
