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

import javafx.scene.control.CheckBox;

/**
 * Utilities to get the type of schematic the user wants.
 *
 * @author Jeff Chen
 */
public class SchematicTypes {

    /**
     * Prevent instantiation.
     */
    private SchematicTypes() {
    }

    /**
     * Get the selected schematic export type from the three checkboxes.
     *
     * @param schematic The "Schematic" checkbox.
     * @param bo2       The "bo2" checkbox.
     * @param bo3       The "bo3" checkbox.
     * @return Returns the type of schematic the user wants to export.
     * @throws UnsupportedOperationException If the type is 1)Not supported or 2)None of the
     *                                       checkboxes are selected.
     */
    public static SchematicType getTypeFrom(CheckBox schematic, CheckBox bo2, CheckBox bo3) throws UnsupportedOperationException {
        if(schematic.isSelected()) {
            return McEditSchematic.getInstance();
        }
        if(bo2.isSelected()) {
            return BiomeWorldV2.getInstance();
        } else if(bo3.isSelected())  {
            throw new UnsupportedOperationException("BO3 is not supported yet!");
        } else {
            throw new UnsupportedOperationException("Unknown!");
        }
    }
}
