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

package net.nostromo.libc.struct.c;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class sockaddr_ll extends Structure {

    public short sll_family;
    public short sll_protocol;
    public int sll_ifindex;
    public short sll_hatype;
    public byte sll_pkttype;
    public byte sll_halen;
    public byte[] sll_addr = new byte[8];

    public sockaddr_ll() { }

    public sockaddr_ll(final Pointer p) {
        super(p);
    }

    protected List<?> getFieldOrder() {
        return Arrays.asList("sll_family", "sll_protocol", "sll_ifindex", "sll_hatype", "sll_pkttype", "sll_halen",
                "sll_addr");
    }
}
