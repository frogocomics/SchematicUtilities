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

package com.gmail.frogocomics.utils;

import com.gmail.frogocomics.schematic.Schematics;
import com.gmail.frogocomics.schematic.gui.Main;

import java.io.File;

public class JavaFxUtils {

    private JavaFxUtils() {
    }

    public static void reset(File tempDirectory) {
        Schematics.schematics.clear();
        Main.getInstance().getPrimaryStage().setScene(Main.getInstance().getMainScene());
        Main.getInstance().uploadFiles.setDisable(false);
        Main.getInstance().listView.setDisable(false);
        Main.getInstance().listView.getItems().clear();
        Main.getInstance().loadSchematics.setDisable(false);
        Main.getInstance().filesSelected.setText("There are currently 0 files selected");
        ((Runnable) tempDirectory::delete).run();
    }
}
