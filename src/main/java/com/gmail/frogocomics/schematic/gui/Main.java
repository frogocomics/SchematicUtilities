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

import com.gmail.frogocomics.schematic.BiomeWorldV2Object;
import com.gmail.frogocomics.schematic.McEditSchematicObject;
import com.gmail.frogocomics.schematic.SchematicLocation;
import com.gmail.frogocomics.schematic.Schematics;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import klaue.mcschematictool.exceptions.ClassicNotSupportedException;
import klaue.mcschematictool.exceptions.ParseException;

public final class Main extends Application {

    private static Main instance;
    public Label filesSelected = new Label("There are currently 0 files selected");
    public Button uploadFiles = new Button("Add/Set");
    public Button loadSchematics = new Button("Load");
    public ListView<SchematicLocation> listView = new ListView<>();
    public Button editing = new Button("Go to editing");
    private Stage primaryStage;
    private Scene primaryScene;
    private GridPane root;
    private ObservableList<SchematicLocation> schematics;

    public Main() {
        super();
        synchronized (Main.class) {
            if (instance != null) {
                throw new UnsupportedOperationException();
            }
            instance = this;
        }
    }

    public static Main getInstance() {
        return Main.instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.root = new GridPane();
        this.primaryScene = new Scene(this.root, 1000, 600, Color.AZURE);

        Label title = new Label("Schematic Utilities");
        title.setId("schematic-utilities");
        title.setPrefWidth(this.primaryScene.getWidth() + 500);
        this.root.add(title, 0, 0);

        filesSelected.setId("files-selected");
        this.root.add(filesSelected, 0, 1);

        Region spacer1 = new Region();
        spacer1.setPrefWidth(30);
        spacer1.setPrefHeight(30);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(30);
        spacer2.setPrefHeight(30);

        Region spacer3 = new Region();
        spacer3.setPrefWidth(30);
        spacer3.setPrefHeight(250);

        Region spacer4 = new Region();
        spacer4.setPrefWidth(1000);
        spacer4.setPrefHeight(10);

        Region spacer5 = new Region();
        spacer5.setPrefWidth(30);
        spacer5.setPrefHeight(30);

        Region spacer6 = new Region();
        spacer6.setPrefWidth(1000);
        spacer6.setPrefHeight(10);

        Region spacer7 = new Region();
        spacer7.setPrefWidth(1000);
        spacer7.setPrefHeight(100);

        Region spacer8 = new Region();
        spacer8.setPrefWidth(30);
        spacer8.setPrefHeight(30);

        this.root.add(spacer4, 0, 3);

        listView.setId("schematic-list");
        listView.setEditable(false);
        listView.setPrefWidth(500);
        listView.setPrefHeight(250);
        this.root.add(new HBox(spacer3, listView), 0, 4);

        uploadFiles.setPadding(new Insets(5, 5, 5, 5));
        uploadFiles.setPrefWidth(120);
        uploadFiles.setPrefHeight(30);
        uploadFiles.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select schematic(s)");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MCEdit Schematic File", "*.schematic"), new FileChooser.ExtensionFilter("Biome World Object Version 2", "*.bo2"));
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(this.primaryStage);
            if (selectedFiles != null) {
                if (selectedFiles.size() == 1) {
                    filesSelected.setText("There is currently 1 file selected");
                } else {
                    filesSelected.setText("There are currently " + String.valueOf(selectedFiles.size()) + " files selected");
                }
                ObservableList<SchematicLocation> selectedSchematicFiles = FXCollections.observableArrayList();
                selectedSchematicFiles.addAll(selectedFiles.stream().map(f -> new SchematicLocation(f, f.getName())).collect(Collectors.toList()));
                listView.setItems(selectedSchematicFiles);
                this.schematics = selectedSchematicFiles;
            }

        });
        this.root.add(new HBox(spacer1, uploadFiles, spacer2, new Label("Only .schematic files are allowed at this point!")), 0, 2);

        editing.setPadding(new Insets(5, 5, 5, 5));
        editing.setPrefWidth(240);
        editing.setPrefHeight(30);
        editing.setDisable(true);
        editing.setOnAction(event -> this.primaryStage.setScene(Editing.getScene()));
        this.root.add(new HBox(spacer8, editing), 0, 8);

        loadSchematics.setPadding(new Insets(5, 5, 5, 5));
        loadSchematics.setPrefWidth(120);
        loadSchematics.setPrefHeight(30);
        loadSchematics.setOnAction(event -> {
            if (this.schematics != null) {
                ((Runnable) () -> {
                    for (SchematicLocation location : this.schematics) {
                        if (FilenameUtils.isExtension(location.getLocation().getName(), "schematic")) {
                            try {
                                Schematics.schematics.add(McEditSchematicObject.load(location.getLocation()));
                            } catch (ParseException | ClassicNotSupportedException | IOException e) {
                                e.printStackTrace();
                            }
                        } else if (FilenameUtils.isExtension(location.getLocation().getName(), "bo2")) {
                            try {
                                Schematics.schematics.add(BiomeWorldV2Object.load(location.getLocation()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).run();
                loadSchematics.setDisable(true);
                uploadFiles.setDisable(true);
                listView.setDisable(true);
                editing.setDisable(false);
            }
        });
        this.root.add(spacer6, 0, 5);
        this.root.add(new HBox(spacer5, loadSchematics), 0, 6);
        this.root.add(spacer7, 0, 7);

        this.primaryScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800");
        this.primaryScene.getStylesheets().add(new File("style.css").toURI().toURL().toExternalForm());
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Schematic Utilities - by frogocomics");
        this.primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public Scene getMainScene() {
        return this.primaryScene;
    }
}
