package com.gmail.frogocomics.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private ZipUtils() {
    }

    /**
     * Zip a list of file into one zip file. Credits for this code go to <a
     * href="http://www.java2s.com/Code/Java/File-Input-Output/Zipalistoffileintoonezipfile.htm">Java2s</a>.
     *
     * @param targetZipFile target zip file
     * @throws IOException IO error exception can be thrown when copying ...
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
