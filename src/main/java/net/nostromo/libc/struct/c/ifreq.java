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

public class ifreq extends Structure {

    public static final int IF_NAMESIZE = 16;

    public byte[] ifrn_name = new byte[IF_NAMESIZE];
    public sockaddr ifru_addr;
    public sockaddr ifru_dstaddr;
    public sockaddr ifru_broadaddr;
    public sockaddr ifru_netmask;
    public sockaddr ifru_hwaddr;
    public short ifru_flags;
    public int ifru_ivalue;
    public int ifru_mtu;
    public ifmap ifru_map;
    public byte[] ifru_slave = new byte[IF_NAMESIZE];
    public byte[] ifru_newname = new byte[IF_NAMESIZE];
    public Pointer ifru_data;

    public ifreq() { }

    public ifreq(final Pointer p) {
        super(p);
    }

    @Override
    protected List getFieldOrder() {
        return Arrays.asList("ifrn_name", "ifru_addr", "ifru_dstaddr", "ifru_broadaddr", "ifru_netmask", "ifru_hwaddr",
                "ifru_flags", "ifru_ivalue", "ifru_mtu", "ifru_map", "ifru_slave", "ifru_newname", "ifru_data");
    }
}
