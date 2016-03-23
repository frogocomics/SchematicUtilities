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

public class McEditSchematic implements SchematicType {

    private static McEditSchematic instance = new McEditSchematic();

    private McEditSchematic() {
    }

    public static McEditSchematic getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "MCEdit Schematic";
    }

    @Override
    public SchematicExporter getExporter(File target) {
        return new McEditSchematicExporter(target);
    }

}
