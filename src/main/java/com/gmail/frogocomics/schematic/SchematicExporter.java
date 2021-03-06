/*
 *     Schematic Utilities, a program used to convert between schematic formats.
 *     Copyright (C) 2016  Jeff Chen
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gmail.frogocomics.schematic;

import java.io.File;

/**
 * A exporter, to export a specific schematic type.
 *
 * @author Jeff Chen
 */
public abstract class SchematicExporter {

    /**
     * Export the schematics to a particular location
     *
     * @param tempDirectory The temporary directory created by the system. (Usually in
     *                      AppData/Roaming)
     */
    public abstract void exportTo(File tempDirectory);
}
