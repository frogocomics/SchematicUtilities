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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is a location to store the schematics the user would like the convert and/or optimize
 * in memory.
 *
 * @author Jeff Chen
 */
public class Schematics {

    public static List<SchematicObject> schematics = new LinkedList<>();

    /**
     * Get the schematics.
     *
     * @return Returns the schematics.
     */
    public static List<SchematicObject> getSchematics() {
        return Schematics.schematics;
    }

    /**
     * Optimize all the schematics. This removes any extra air along the edges, thus reducing file
     * size.
     */
    public static void optimize() {
        ListIterator<SchematicObject> iterator = schematics.listIterator();
        while (iterator.hasNext()) {
            SchematicObject next = iterator.next();
            next.getContent().trim();
            iterator.set(next);
        }
    }

}
