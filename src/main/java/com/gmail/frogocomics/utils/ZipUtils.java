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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utilities used for zipping up files.
 *
 * @author Jeff Chen
 */
public class ZipUtils {

    /**
     * Prevent instantiation.
     */
    private ZipUtils() {
    }

    /**
     * Zip a list of file into one zip file. Credits for this code go to <a
     * href="http://www.java2s.com/Code/Java/File-Input-Output/Zipalistoffileintoonezipfile.htm">Java2s</a>.
     *
     * @param targetZipFile The target location to be exported to.
     * @throws IOException This exception may be thrown when copying.
     */
    public static void zip(final File tempLocation, final File targetZipFile) throws IOException {

        File[] files = tempLocation.listFiles();
        int fileLength = files.length;

        try {

            FileOutputStream fos = new FileOutputStream(targetZipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            byte[] buffer = new byte[128];

            for (int i = 0; i < fileLength; i++) {
                File currentFile = files[i];
                if (!currentFile.isDirectory()) {
                    ZipEntry entry = new ZipEntry(currentFile.getName());
                    FileInputStream fis = new FileInputStream(currentFile);
                    zos.putNextEntry(entry);
                    int read = 0;
                    while ((read = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, read);
                    }
                    zos.closeEntry();
                    fis.close();
                }
            }

            zos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
        }

    }
}
