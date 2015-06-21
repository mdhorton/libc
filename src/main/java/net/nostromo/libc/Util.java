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

package net.nostromo.libc;

import com.sun.jna.Native;

import java.nio.ByteOrder;

public class Util {

    public static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
    public static final int SIZE_OF_INT = Native.getNativeSize(Integer.class);

    public static short htons(final short val) {
        if (BIG_ENDIAN) return val;
        return Short.reverseBytes(val);
    }

    public static String inetNtoa(final int address) {
        return ((address >>> 24) & 0xFF) + "." +
                ((address >>> 16) & 0xFF) + "." +
                ((address >>> 8) & 0xFF) + "." +
                (address & 0xFF);
    }

    public static String bytesToMac(final byte[] bytes) {
        final StringBuilder sb = new StringBuilder((bytes.length * 3) - 1);

        for (final byte b : bytes) {
            if (sb.length() > 0) sb.append(":");
            sb.append(Character.forDigit((b >>> 4) & 0xF, 16));
            sb.append(Character.forDigit((b & 0xF), 16));
        }

        return sb.toString();
    }
}
