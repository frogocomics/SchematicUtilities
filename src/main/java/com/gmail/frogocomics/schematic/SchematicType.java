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
 * Represents a type of schematic.
 *
 * @author Jeff Chen
 */
public interface SchematicType {

    /**
     * Get the name of the schematic.
     *
     * @return Returns the name of the schematic.
     */
    String getName();

    /**
     * Get the exporter of the schematic type.
     *
     * @param target The location to export to.
     * @return Returns the exporter of the schematic type.
     */
    SchematicExporter getExporter(File target);

}
