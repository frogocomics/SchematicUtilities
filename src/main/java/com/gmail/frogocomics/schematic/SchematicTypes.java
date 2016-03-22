package com.gmail.frogocomics.schematic;

import javafx.scene.control.CheckBox;

public class SchematicTypes {

    private SchematicTypes() {
    }

    public static SchematicType getTypeFrom(CheckBox schematic, CheckBox bo2, CheckBox bo3) {
        if(schematic.isSelected()) {
            return McEditSchematic.getInstance();
        } else if(bo2.isSelected()) {
            return BiomeWorldV2.getInstance();
        } else {
            throw new UnsupportedOperationException("BO3 is not supported yet!");
        }
    }
}
