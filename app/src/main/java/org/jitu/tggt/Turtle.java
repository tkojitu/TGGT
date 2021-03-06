package org.jitu.tggt;

import android.gesture.GesturePoint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

public class Turtle {
    String name;
    private float x = 0.0f;
    private float y = 0.0f;
    private float direction = 0.0f;
    private int speed = 5;
    private boolean drawing = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ArrayList<ArrayList<GesturePoint>> strokes = new ArrayList<>();

    public Turtle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ArrayList<GesturePoint>> getStrokes() {
        return strokes;
    }

    public void warp(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        float dx = (float) (speed * Math.cos(Math.toRadians(direction)) + x);
        float dy = (float) (speed * Math.sin(Math.toRadians(direction)) + y);
        if (drawing) {
            strokes.get(strokes.size() - 1).add(new GesturePoint(dx, dy, System.currentTimeMillis()));
        }
        x = dx;
        y = dy;
    }

    public void turn(float degrees) {
        direction += degrees;
        direction %= 360.0f;
    }

    public void penUp() {
        drawing = false;
    }

    public void penDown() {
        drawing = true;
        ArrayList<GesturePoint> stroke = new ArrayList<>();
        stroke.add(new GesturePoint(x, y, System.currentTimeMillis()));
        strokes.add(stroke);
    }

    public void setSpeed(int arg) {
        speed = arg;
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GesturePoint> stroke : strokes) {
            drawStroke(canvas, stroke);
        }
    }

    private void drawStroke(Canvas canvas, ArrayList<GesturePoint> stroke) {
        if (stroke.isEmpty()) {
            return;
        }
        GesturePoint src = stroke.get(0);
        for (int i = 1; i < stroke.size(); ++i) {
            GesturePoint dst = stroke.get(i);
            canvas.drawLine(src.x, src.y, dst.x, dst.y, paint);
            src = dst;
        }
    }
}
