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

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import klaue.mcschematictool.Slice;
import klaue.mcschematictool.SliceStack;
import klaue.mcschematictool.blocktypes.Block;

public class BiomeWorldV2Object extends SchematicObject {

    private SliceStack content;
    private String name;

    private BiomeWorldV2Object(SliceStack content, String name) {
        this.content = content;
        this.name = name;
    }

    public static BiomeWorldV2Object load(File file) throws IOException {

        BufferedReader settingsReader;

        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        settingsReader = new BufferedReader(new FileReader(file));
        int lineNumber = 0;
        String thisLine;
        ArrayList<BiomeWorldObjectBlock> bo2Blocks = new ArrayList<>();

        while ((thisLine = settingsReader.readLine()) != null) {
            lineNumber++;
            if (Pattern.compile("[0-9]").matcher(thisLine.substring(0, 1)).matches() || thisLine.substring(0, 1).equalsIgnoreCase("-")) {
                //Example: -1,-1,5:18.4
                // x,z,y:id.data
                String[] location = thisLine.split(":")[0].split(",");
                String[] block = thisLine.split(":")[1].split("\\.");
                bo2Blocks.add(new BiomeWorldObjectBlock(Integer.parseInt(location[0]), Integer.parseInt(location[2]), Integer.parseInt(location[1]), Short.parseShort(block[0]), Byte.parseByte(block[1])));
            }
        }

        ArrayList<Integer> maxXMap = new ArrayList<>();
        ArrayList<Integer> maxYMap = new ArrayList<>();
        ArrayList<Integer> maxZMap = new ArrayList<>();
        for (BiomeWorldObjectBlock bo2 : bo2Blocks) {
            maxXMap.add(bo2.getX());
            maxYMap.add(bo2.getY());
            maxZMap.add(bo2.getZ());
        }

        int maxX = Collections.max(maxXMap);
        int maxY = Collections.max(maxYMap);
        int maxZ = Collections.max(maxZMap);
        int minX = Collections.min(maxXMap);
        int minY = Collections.min(maxYMap);
        int minZ = Collections.min(maxZMap);
        int differenceX = maxX - minX + 1;
        int differenceY = maxY - minY + 1;
        int differenceZ = maxZ - minZ + 1;

        HashMap<Integer, Set<BiomeWorldObjectBlock>> blocks = new HashMap<>();
        for (int i = 0; i < differenceY + 1; i++) {
            blocks.put(i, new HashSet<>());
        }

        for (BiomeWorldObjectBlock bo2 : bo2Blocks) {
            Set<BiomeWorldObjectBlock> a = blocks.get(bo2.getY() - minY);
            a.add(bo2);
            blocks.replace(bo2.getY(), a);
        }

        //System.out.println(differenceX + " " + differenceZ);
        SliceStack schematic = new SliceStack(differenceY, differenceX, differenceZ);

        for (Map.Entry<Integer, Set<BiomeWorldObjectBlock>> next : blocks.entrySet()) {
            Slice slice = new Slice(differenceX, differenceZ);
            for (BiomeWorldObjectBlock block : next.getValue()) {
                //System.out.println("Added block at " + String.valueOf(block.getX() - minX) + "," + String.valueOf(block.getZ() - minZ));
                slice.setBlock(block.getBlock(), block.getX() - minX, block.getZ() - minZ);
            }
            schematic.addSlice(slice);
        }
        //System.out.println(schematic.toString());

        return new BiomeWorldV2Object(schematic, FilenameUtils.getBaseName(file.getAbsolutePath()));
    }

    @Override
    public SliceStack getContent() {
        return this.content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getExtension() {
        return ".bo2";
    }

    @Override
    public SchematicType getType() {
        return null;
    }

    private static class BiomeWorldObjectBlock {

        private int x;
        private int y;
        private int z;
        private Block block;

        public BiomeWorldObjectBlock(int x, int y, int z, short id, byte data) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.block = Block.getInstance(id, data);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getZ() {
            return this.z;
        }

        public Block getBlock() {
            return this.block;
        }

    }

}
