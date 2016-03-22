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
