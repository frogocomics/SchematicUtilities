package com.gmail.frogocomics.schematic;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

import klaue.mcschematictool.SchematicReader;
import klaue.mcschematictool.SliceStack;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

public class McEditSchematicObject extends SchematicObject {

    private SliceStack content;
    private String name;

    private McEditSchematicObject(SliceStack content, String name) {
        this.content = content;
        this.name = name;
    }

    public static McEditSchematicObject load(File file) throws ParseException, IOException, ClassicNotSupportedException {
        return new McEditSchematicObject(SchematicReader.readSchematicsFile(file), FilenameUtils.getBaseName(file.getAbsolutePath()));
    }

    public static McEditSchematicObject createEmpty() {
        return new McEditSchematicObject(new SliceStack(), null);
    }

    @Override
    public SliceStack getContent() {
        return content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getExtension() {
        return ".schematic";
    }

    @Override
    public SchematicType getType() {
        return McEditSchematic.getInstance();
    }

}
