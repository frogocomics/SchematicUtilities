package com.gmail.frogocomics.schematic;

import com.gmail.frogocomics.schematic.gui.Main;
import com.gmail.frogocomics.utils.ZipUtils;

import java.io.File;
import java.io.IOException;

import klaue.mcschematictool.SchematicWriter;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

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
            Schematics.schematics.clear();

            //<editor-fold desc="Reset everything">
            Main.getInstance().getPrimaryStage().setScene(Main.getInstance().getMainScene());
            Main.getInstance().uploadFiles.setDisable(false);
            Main.getInstance().listView.setDisable(false);
            Main.getInstance().listView.getItems().clear();
            Main.getInstance().loadSchematics.setDisable(false);
            Main.getInstance().filesSelected.setText("There are currently 0 files selected");
            ((Runnable) tempDirectory::delete).run();
            //</editor-fold>

        } catch (IOException | ParseException | ClassicNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
