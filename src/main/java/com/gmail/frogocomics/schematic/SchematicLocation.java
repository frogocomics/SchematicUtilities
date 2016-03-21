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
