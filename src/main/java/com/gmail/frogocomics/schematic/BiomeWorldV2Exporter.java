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

import com.gmail.frogocomics.utils.JavaFxUtils;
import com.gmail.frogocomics.utils.ZipUtils;
import com.gmail.frogocomics.utils.annotations.ExporterOf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import klaue.mcschematictool.Slice;
import klaue.mcschematictool.blocktypes.Block;

@ExporterOf(object = BiomeWorldV2Object.class, type = BiomeWorldV2.class)
public class BiomeWorldV2Exporter extends SchematicExporter {

    private File target;

    public BiomeWorldV2Exporter(File target) {
        this.target = target;
    }

    @Override
    public void exportTo(File tempDirectory) {
        for(SchematicObject schematicToExport : Schematics.getSchematics()) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(tempDirectory.getAbsolutePath() + "\\" + schematicToExport.getName() + ".bo2"));
                // <editor-fold desc="Write defaults">
                //Write BO2 defaults
                //[META]
                //version=2.0
                //spawnOnBlockType=2
                //spawnSunlight=True
                //spawnDarkness=True
                //spawnWater=False
                //spawnLava=False
                //underFill=False
                //randomRotation=True
                //dig=True
                //tree=True
                //branch=False
                //needsFoundation=True
                //rarity=100
                //collisionPercentage=20
                //spawnElevationMin=0
                //spawnElevationMax=206
                //spawnInBiome=Custom
                out.write("[META]");
                out.newLine();
                out.write("version=2.0");
                out.newLine();
                out.write("spawnOnBlockType=2");
                out.newLine();
                out.write("spawnSunlight=True");
                out.newLine();
                out.write("spawnDarkness=True");
                out.newLine();
                out.write("spawnWater=False");
                out.newLine();
                out.write("spawnLava=False");
                out.newLine();
                out.write("underFill=False");
                out.newLine();
                out.write("randomRotation=True");
                out.newLine();
                out.write("dig=True");
                out.newLine();
                out.write("tree=True");
                out.newLine();
                out.write("branch=False");
                out.newLine();
                out.write("needsFoundation=True");
                out.newLine();
                out.write("spawnElevationMin=0");
                out.newLine();
                out.write("spawnElevationMax=256");
                out.newLine();
                out.write("spawnInBiome=Custom");
                out.newLine();
                out.write("[DATA]");
                out.newLine();
                // </editor-fold>
                //BO2 Block Data: X,Z,Y:ID.DATA
                for(int y = 0; y < schematicToExport.getContent().getHeight(); y++) {
                    Slice slice = schematicToExport.getContent().getSlice(y);
                    for(int x = 0; x < (slice.getX() - 1); x++) {
                        for(int z = 0; z < (slice.getZ() - 1); z++) {
                            Block block = slice.getBlockAt(x, z);
                            if(block.getId() != 0) {
                                out.write(String.valueOf(x) + "," + String.valueOf(z) + "," + String.valueOf(y) + ":" + String.valueOf(block.getId()) + "," + String.valueOf(block.getData()));
                                out.newLine();
                            }
                        }
                    }
                }
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            ZipUtils.zip(tempDirectory, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JavaFxUtils.reset(tempDirectory);
    }
}
