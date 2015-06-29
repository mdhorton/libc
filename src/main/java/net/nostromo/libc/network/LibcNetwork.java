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

package net.nostromo.libc.network;

import net.nostromo.libc.Libc;

public class LibcNetwork extends Libc implements LibcNetworkConstants {

    public native int socket(int domain, int type, int protocol);

    public native int setsockopt(int fd, int level, int optname, long ptr_optval, int optlen);

    public native int bind(int fd, long ptr_address, int address_len);
}
