package com.gmail.frogocomics.schematic;

import java.io.File;

public interface SchematicType {

    String getName();

    SchematicExporter getExporter(File target);

}
