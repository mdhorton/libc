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
import net.nostromo.libc.struct.Union;
import net.nostromo.libc.struct.network.socket.SockAddr;

// ifreq (linux/if.h)
public class IfReqRuUnion extends Union {

    // total bytes
    public static final int SIZE = 24;

    public enum Name implements FieldName {
        ADDR, DSTADDR, BROADADDR, NETMASK, HWADDR, FLAGS, IFINDEX, MTU, MAP, SLAVE, NEWNAME, DATA
    }

    public SockAddr addr;
    public SockAddr dstaddr;
    public SockAddr broadaddr;
    public SockAddr netmask;
    public SockAddr hwaddr;
    public short flags;         // 16
    public int ifindex;         // 32
    public int mtu;             // 32
    public IfMap map;
    public byte[] slave;        // 8[IF_NAMESIZE]
    public byte[] newname;      // 8[IF_NAMESIZE]
    public long ptr_data;       // u64

    public IfReqRuUnion(final Name name) {
        setFieldName(name);
    }

    @Override
    public void setFieldName(final FieldName unionField, final boolean instantiateObjects) {
        this.fieldName = unionField;

        if (unionField == Name.ADDR) addr = new SockAddr();
        else if (unionField == Name.DSTADDR) dstaddr = new SockAddr();
        else if (unionField == Name.BROADADDR) broadaddr = new SockAddr();
        else if (unionField == Name.NETMASK) netmask = new SockAddr();
        else if (unionField == Name.HWADDR) hwaddr = new SockAddr();
        else if (unionField == Name.MAP) map = new IfMap();
        else if (unionField == Name.SLAVE) slave = new byte[IfReq.IF_NAMESIZE];
        else if (unionField == Name.NEWNAME) newname = new byte[IfReq.IF_NAMESIZE];
    }

    @Override
    public void read(final OffHeapBuffer buffer) {
        if (fieldName == Name.ADDR) addr.read(buffer);
        else if (fieldName == Name.DSTADDR) dstaddr.read(buffer);
        else if (fieldName == Name.BROADADDR) broadaddr.read(buffer);
        else if (fieldName == Name.NETMASK) netmask.read(buffer);
        else if (fieldName == Name.HWADDR) hwaddr.read(buffer);
        else if (fieldName == Name.FLAGS) flags = buffer.getShort();
        else if (fieldName == Name.IFINDEX) ifindex = buffer.getInt();
        else if (fieldName == Name.MTU) mtu = buffer.getInt();
        else if (fieldName == Name.MAP) map.read(buffer);
        else if (fieldName == Name.SLAVE) buffer.getBytes(slave);
        else if (fieldName == Name.NEWNAME) buffer.getBytes(newname);
        else if (fieldName == Name.DATA) ptr_data = buffer.getLong();
    }

    @Override
    public void write(final OffHeapBuffer buffer) {
        if (fieldName == Name.ADDR) addr.write(buffer);
        else if (fieldName == Name.DSTADDR) dstaddr.write(buffer);
        else if (fieldName == Name.BROADADDR) broadaddr.write(buffer);
        else if (fieldName == Name.NETMASK) netmask.write(buffer);
        else if (fieldName == Name.HWADDR) hwaddr.write(buffer);
        else if (fieldName == Name.FLAGS) buffer.setShort(flags);
        else if (fieldName == Name.IFINDEX) buffer.setInt(ifindex);
        else if (fieldName == Name.MTU) buffer.setInt(mtu);
        else if (fieldName == Name.MAP) map.write(buffer);
        else if (fieldName == Name.SLAVE) buffer.setBytes(slave);
        else if (fieldName == Name.NEWNAME) buffer.setBytes(newname);
        else if (fieldName == Name.DATA) buffer.setLong(ptr_data);
    }
}
