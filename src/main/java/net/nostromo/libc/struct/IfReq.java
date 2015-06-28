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

// ifreq (linux/if.h)
public class IfReq extends Struct {

    // total bytes
    public static final int SIZE = IfReqRn.SIZE + IfReqRu.SIZE;

    public static final int IF_NAMESIZE = 16;

    public IfReqRn ifrn;
    public IfReqRu ifru;

    public IfReq() {
        super(SIZE);
    }

    public IfReq(final IfReqRn.Name rnName, final IfReqRu.Name ruName) {
        this();
        ifrn = new IfReqRn(buffer);
        ifru = new IfReqRu(buffer);
        ifrn.setFieldName(rnName);
        ifru.setFieldName(ruName);
    }

    @Override
    protected void read(final NativeHeapBuffer buffer) {
        ifrn.read(buffer);
        ifru.read(buffer);
    }

    @Override
    protected void write(final NativeHeapBuffer buffer) {
        ifrn.write(buffer);
        ifru.write(buffer);
    }
}
