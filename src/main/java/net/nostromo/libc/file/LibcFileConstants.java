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

package net.nostromo.libc.file;

public interface LibcFileConstants {

    // file open flags
    int O_RDONLY = 00;
    int O_WRONLY = 01;
    int O_RDWR = 02;
    int O_CREAT = 0100;
    int O_EXCL = 0200;
    int O_NOCTTY = 0400;
    int O_TRUNC = 01000;
    int O_APPEND = 02000;
    int O_NONBLOCK = 04000;

    // file modes
    int S_IRUSR = 0400;
    int S_IWUSR = 0200;
    int S_IXUSR = 0100;
    int S_IRWXU = S_IRUSR | S_IWUSR | S_IXUSR;
    int S_IRGRP = S_IRUSR >> 3;
    int S_IWGRP = S_IWUSR >> 3;
    int S_IXGRP = S_IXUSR >> 3;
    int S_IRWXG = S_IRWXU >> 3;
    int S_IROTH = S_IRGRP >> 3;
    int S_IWOTH = S_IWGRP >> 3;
    int S_IXOTH = S_IXGRP >> 3;
    int S_IRWXO = S_IRWXG >> 3;

    // file seek
    int SEEK_SET = 0;
    int SEEK_CUR = 1;
    int SEEK_END = 2;
    int SEEK_DATA = 3;
    int SEEK_HOLE = 4;
    int SEEK_MAX = SEEK_HOLE;
}
