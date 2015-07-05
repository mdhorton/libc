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

import net.nostromo.libc.OffHeapBuffer;

public abstract class Union extends Struct {

    public interface FieldName {}

    protected FieldName fieldName;

    public Union() {}

    public Union(final boolean instantiateObjects) {
        super(instantiateObjects);
    }

    public Union(final OffHeapBuffer buffer) {
        super(buffer);
    }

    public void setFieldName(FieldName fieldName) {
        setFieldName(fieldName, instantiateObjects);
    }

    public abstract void setFieldName(FieldName fieldName, boolean instantiateObjects);

    public FieldName getFieldName() {
        return fieldName;
    }
}
