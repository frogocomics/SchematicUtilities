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

import com.gmail.frogocomics.utils.JavaFxUtils;
import com.gmail.frogocomics.utils.ZipUtils;
import com.gmail.frogocomics.utils.annotations.ExporterOf;

import java.io.File;
import java.io.IOException;

import klaue.mcschematictool.SchematicWriter;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

@ExporterOf(object = McEditSchematicObject.class, type = McEditSchematic.class)
public class McEditSchematicExporter extends SchematicExporter {

    private File target;

    public McEditSchematicExporter(File target) {
        this.target = target;
    }

    @Override
    public void exportTo(File tempDirectory) {
        try {
            for (SchematicObject schematicToExport : Schematics.getSchematics()) {
                File schematicLocation = new File(tempDirectory.getAbsolutePath() + "\\" + schematicToExport.getName() + schematicToExport.getExtension());
                SchematicWriter.writeSchematicsFile(schematicToExport.getContent(), schematicLocation);
            }

            ZipUtils.zip(tempDirectory, target);

            JavaFxUtils.reset(tempDirectory);

        } catch (IOException | ParseException | ClassicNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
