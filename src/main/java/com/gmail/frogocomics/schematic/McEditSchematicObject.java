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

import java.io.File;
import java.io.IOException;

import klaue.mcschematictool.SchematicReader;
import klaue.mcschematictool.SliceStack;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

/**
 * Represents a MCEdit (or WorldEdit) schematic.
 *
 * <p>The .schematic file format was created by the community to store sections of a Minecraft world
 * for use with third-party programs (specifically, MCEdit, Minecraft Note Block Studio, Redstone
 * Simulator, and WorldEdit). Schematics are in NBT format and are loosely based on the Indev level
 * format. Indices for the Blocks and Data arrays are ordered YZX - that is, the X coordinate varies
 * the fastest. This is similar to block ordering for Classic, Indev, and Anvil levels. This format
 * cannot store block IDs higher than 255 (which are currently not used in vanilla), nor can it
 * distinguish air blocks that should overwrite existing blocks from those that shouldn't.</p>
 *
 * @author Jeff Chen
 */
public class McEditSchematicObject extends SchematicObject {

    private SliceStack content;
    private String name;

    private McEditSchematicObject(SliceStack content, String name) {
        this.content = content;
        this.name = name;
    }

    /**
     * Load a new {@link McEditSchematic} from a {@link File}.
     *
     * @param file The file to load from.
     * @return Returns a new schematic, unless an exception happens.
     * @throws ParseException Exception for parsing errors.
     * @throws IOException When loading the file goes wrong.
     * @throws ClassicNotSupportedException An exception for classic schematics.
     */
    public static McEditSchematicObject load(File file) throws ParseException, IOException, ClassicNotSupportedException {
        return new McEditSchematicObject(SchematicReader.readSchematicsFile(file), FilenameUtils.getBaseName(file.getAbsolutePath()));
    }

    /**
     * Get the content of the schematic.
     *
     * @return Returns the content of the schematic.
     */
    @Override
    public SliceStack getContent() {
        return content;
    }

    /**
     * Gets the name without the extension of the schematic.
     *
     * @return Returns the name of the schematic (without extension)
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the file extension of this format.
     *
     * @return Returns the file extension.
     */
    @Override
    public String getExtension() {
        return ".schematic";
    }

    /**
     * Get the type of this schematic.
     *
     * @return Returns the type of this schematic.
     */
    @Override
    public SchematicType getType() {
        return McEditSchematic.getInstance();
    }

}
