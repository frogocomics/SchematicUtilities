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
        throw new UnsupportedOperationException("Not supported yet!");
    }

}
