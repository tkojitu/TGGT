package org.jitu.tggt;

import android.graphics.Canvas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VirtualMachine {
    private int pc = -1;
    private Program program;
    private ArrayList<Turtle> turtles = new ArrayList<>();

    public VirtualMachine() {
    }

    public void load(File file) throws IOException, SyntaxErrorException {
        Compiler compiler = new Compiler();
        program = compiler.compile(file);
        turtles.clear();
        pc = -1;
    }

    public void run() {
        pc = 0;
    }

    public void execute(Canvas canvas) {
        if (pc < 0) {
            drawLastTurtle(canvas);
            return;
        }
        Command command = program.getCommand(pc);
        if (command != null) {
            command.execute(this);
        }
        ++pc;
        drawLastTurtle(canvas);
    }

    public void drawLastTurtle(Canvas canvas) {
        Turtle turtle = getLastTurtle();
        if (turtle == null) {
            return;
        }
        turtle.draw(canvas);
    }

    public Turtle getLastTurtle() {
        if (turtles.isEmpty()) {
            return null;
        }
        return turtles.get(turtles.size() - 1);
    }

    public void move() {
        getLastTurtle().move();
    }

    public void warp(float x, float y) {
        getLastTurtle().warp(x, y);
    }

    public void turn(float degrees) {
        getLastTurtle().turn(degrees);
    }

    public void penUp() {
        getLastTurtle().penUp();
    }

    public void penDown() {
        getLastTurtle().penDown();
    }

    public void setSpeed(int arg) {
        getLastTurtle().setSpeed(arg);
    }

    public void newTurtle(String name) {
        turtles.add(new Turtle(name));
    }

    public void exit() {
        pc = -2;
    }
}
