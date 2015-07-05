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

public class LibcException extends RuntimeException {

    private final String command;
    private final int returnCode;
    private final String error;

    public LibcException(final String command, final int returnCode, final String error) {
        super(String.format("%s: %s [%d]", command, error, returnCode));
        this.command = command;
        this.returnCode = returnCode;
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getError() {
        return error;
    }
}
