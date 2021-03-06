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

package com.gmail.frogocomics.schematic.gui;

import com.google.common.io.Files;

import com.gmail.frogocomics.schematic.SchematicTypes;
import com.gmail.frogocomics.schematic.Schematics;

import java.io.File;
import java.net.MalformedURLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class Editing {

    private static File tempDirectory;

    private Editing() {}

    public static Scene getScene() {

        Region spacer1 = new Region();
        spacer1.setPrefWidth(1000);
        spacer1.setPrefHeight(10);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(30);

        Region spacer3 = new Region();
        spacer3.setPrefWidth(1000);
        spacer3.setPrefHeight(10);

        Region spacer4 = new Region();
        spacer4.setPrefWidth(30);

        Region spacer5 = new Region();
        spacer5.setPrefWidth(1000);
        spacer5.setPrefHeight(100);

        Region spacer6 = new Region();
        spacer6.setPrefWidth(30);

        Region spacer7 = new Region();
        spacer7.setPrefWidth(30);

        Region spacer8 = new Region();
        spacer8.setPrefWidth(1000);
        spacer8.setPrefHeight(100);

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 1000, 600, Color.AZURE);
        Label title = new Label("Schematic Utilities");
        title.setId("schematic-utilities");
        title.setPrefWidth(scene.getWidth() + 500);
        root.add(title, 0, 0);
        Label filesSelected = new Label("Conversion options");
        filesSelected.setPadding(new Insets(10, 20, 20, 30));
        root.add(filesSelected, 0, 1);
        root.add(spacer1, 0, 2);

        CheckBox schematic = new CheckBox("MCEdit Schematic");
        CheckBox bo2 = new CheckBox("BO2");
        CheckBox bo3 = new CheckBox("BO3");
        schematic.setOnAction(event -> {
            if (schematic.isSelected()) {
                bo2.setSelected(false);
                bo3.setSelected(false);
            }
        });
        bo2.setOnAction(event -> {
            if (bo2.isSelected()) {
                schematic.setSelected(false);
                bo3.setSelected(false);
            }
        });
        bo3.setOnAction(event -> {
            if (bo3.isSelected()) {
                schematic.setSelected(false);
                bo2.setSelected(false);
            }
        });
        bo3.setDisable(true);
        HBox conversionOptions = new HBox(spacer2, schematic, bo2, bo3);
        conversionOptions.setSpacing(10);
        root.add(conversionOptions, 0, 2);
        root.add(spacer3, 0, 3);

        Label optimization = new Label("Optimization options");
        optimization.setPadding(new Insets(10, 20, 20, 30));
        root.add(optimization, 0, 4);

        CheckBox removeExcessAir = new CheckBox("Remove excess air");
        HBox optimizationOptions = new HBox(spacer4, removeExcessAir);
        optimizationOptions.setSpacing(10);
        root.add(optimizationOptions, 0, 5);
        root.add(spacer5, 0, 6);

        Button export = new Button("Export Changes");
        export.setPadding(new Insets(5, 5, 5, 5));
        export.setPrefWidth(340);
        export.setPrefHeight(30);
        export.setDisable(true);
        export.setOnAction(event -> {

            Editing.tempDirectory = Files.createTempDir();

            FileChooser fileSaver = new FileChooser();
            fileSaver.setTitle("Save Files");
            fileSaver.getExtensionFilters().add(new FileChooser.ExtensionFilter("Compressed File", "*.zip"));
            File targetLocation = fileSaver.showSaveDialog(Main.getInstance().getPrimaryStage());

            SchematicTypes.getTypeFrom(schematic, bo2, bo3).getExporter(targetLocation).exportTo(tempDirectory);

        });

        root.add(spacer8, 0, 8);
        root.add(new HBox(spacer7, export), 0, 9);

        Button go = new Button("Begin optimization");
        go.setPadding(new Insets(5, 5, 5, 5));
        go.setPrefWidth(340);
        go.setPrefHeight(30);
        go.setOnAction(event -> {
            if (schematic.isSelected() || bo2.isSelected() || bo3.isSelected()) {
                go.setDisable(true);
                schematic.setDisable(true);
                bo2.setDisable(true);
                bo3.setDisable(true);
                if (removeExcessAir.isSelected()) {
                    Schematics.optimize();
                }
                export.setDisable(false);
            }
        });
        root.add(new HBox(spacer6, go), 0, 7);

        try {
            scene.getStylesheets().add(new File("style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800");

        return scene;
    }

    public static File getTemporaryDirectory() {
        return Editing.tempDirectory;
    }
}
