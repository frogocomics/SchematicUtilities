package com.gmail.frogocomics.schematic;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

import klaue.mcschematictool.SchematicReader;
import klaue.mcschematictool.SliceStack;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

public class McEditSchematic extends Schematic {

    private SliceStack content;
    private String name;

    private McEditSchematic(SliceStack content, String name) {
        this.content = content;
        this.name = name;
    }

    public static McEditSchematic load(File file) throws ParseException, IOException, ClassicNotSupportedException {
        return new McEditSchematic(SchematicReader.readSchematicsFile(file), FilenameUtils.getBaseName(file.getAbsolutePath()));
    }

    @Override
    public SliceStack getContent() {
        return content;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
