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
 * Represents a (file) location a schematic that could be in.
 *
 * @author Jeff Chen
 */
public class SchematicLocation {

    /**
     * The actual location the file is stored in.
     */
    private File location;

    /**
     * The name of the schematic file. (This will contain the extension)
     */
    private String fileName;

    /**
     * Create a new {@link SchematicLocation}.
     *
     * @param location The actual location the file is stored in.
     * @param fileName The name of the schematic file. (This will contain the extension)
     */
    public SchematicLocation(File location, String fileName) {
        this.location = location;
        this.fileName = fileName;
    }

    /**
     * Get the location of the schematic.
     *
     * @return Returns the location of the schematic.
     */
    public File getLocation() {
        return this.location;
    }

    /**
     * Get the file name.
     *
     * @return Returns the file name.
     */
    @Override
    public String toString() {
        return fileName;
    }

}
