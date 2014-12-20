package org.jitu.tggt;

import android.gesture.GesturePoint;
import android.gesture.GestureStore;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Core {
    /**
     * cf. GestureStore.java (android.gesture.GestureStore) and GestureBuilder
     */
    public void dump(ArrayList<Turtle> turtles, File sourceFile) throws IOException {
        File dumpFile = new File(sourceFile.getPath(), "tggt.gestures");
        FileOutputStream fos = new FileOutputStream(dumpFile);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeShort(1); // version
        dos.writeInt(turtles.size()); // number of entries
        long id = System.currentTimeMillis();
        for (Turtle turtle : turtles) {
            dumpTurtle(turtle, id++, dos);
        }
    }

    private void dumpTurtle(Turtle turtle, long id, DataOutputStream dos) throws IOException {
        dos.writeUTF(turtle.getName()); // entry name
        dos.writeInt(1); // number of gestures
        dos.writeLong(id); // gesture id
        ArrayList<ArrayList<GesturePoint>> strokes = turtle.getStrokes();
        dos.writeInt(strokes.size()); // number of strokes
        for (ArrayList<GesturePoint> stroke : strokes) {
            dos.writeInt(stroke.size()); // number of points
            for (GesturePoint point : stroke) {
                dos.writeFloat(point.x); // x coordinate of the point
                dos.writeFloat(point.y); // y coordinate of the point
                dos.writeLong(point.timestamp); // time stamp
            }
        }
    }
}
