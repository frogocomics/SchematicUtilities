package com.gmail.frogocomics.schematic;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is a location to store the schematics the user would like the convert and/or optimize
 * in memory.
 *
 * @author Jeff Chen
 */
public class Schematics {

    public static List<SchematicObject> schematics = new LinkedList<>();

    /**
     * Get the schematics.
     *
     * @return Returns the schematics.
     */
    public static List<SchematicObject> getSchematics() {
        return Schematics.schematics;
    }

    /**
     * Optimize all the schematics. This removes any extra air along the edges, thus reducing file
     * size.
     */
    public static void optimize() {
        ListIterator<SchematicObject> iterator = schematics.listIterator();
        while (iterator.hasNext()) {
            SchematicObject next = iterator.next();
            next.getContent().trim();
            iterator.set(next);
        }
    }

}
