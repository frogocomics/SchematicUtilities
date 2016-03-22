package com.gmail.frogocomics.schematic;

import java.io.File;

/**
 * A class containing various methods to export schematics.
 *
 * @author Jeff Chen
 */
public abstract class SchematicExporter {

    public abstract void exportTo(File tempDirectory);
}
