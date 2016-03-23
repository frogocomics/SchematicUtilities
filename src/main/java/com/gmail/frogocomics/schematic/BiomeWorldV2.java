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

public class BiomeWorldV2 implements SchematicType {

    private static BiomeWorldV2 instance = new BiomeWorldV2();

    private BiomeWorldV2() {
    }

    public static BiomeWorldV2 getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Biome World Object V2";
    }

    @Override
    public SchematicExporter getExporter(File target) {
        return new BiomeWorldV2Exporter(target);
    }

}
