package org.jitu.tggt;

import android.graphics.Canvas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VirtualMachine {
    private Turtle turtle = new Turtle();
    private ArrayList<Command> program = new ArrayList<Command>();
    private int pc = -1;

    public VirtualMachine() {
    }

    public void load(File file) throws IOException, SyntaxErrorException {
        Compiler compiler = new Compiler();
        program = compiler.compile(file);
    }

    public void run() {
        turtle = new Turtle();
        pc = 0;
    }

    public void execute(Canvas canvas) {
        if (pc < 0 || program.size() <= pc) {
            turtle.draw(canvas);
            return;
        }
        Command command = program.get(pc);
        command.execute(this);
        turtle.draw(canvas);
        ++pc;
    }

    public void move() {
        turtle.move();
    }

    public void warp(float x, float y) {
        turtle.warp(x, y);
    }

    public void turn(float degrees) {
        turtle.turn(degrees);
    }

    public void penUp() {
        turtle.penUp();
    }

    public void penDown() {
        turtle.penDown();
    }

    public void setSpeed(int arg) {
        turtle.setSpeed(arg);
    }
}
