package com.gmail.frogocomics.schematic;

import klaue.mcschematictool.SliceStack;

public abstract class SchematicObject {

    public abstract SliceStack getContent();

    public abstract String getName();

    public abstract String getExtension();

    public abstract SchematicType getType();

}
