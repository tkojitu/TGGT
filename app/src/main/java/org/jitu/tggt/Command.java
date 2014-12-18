package org.jitu.tggt;

public abstract class Command {
    protected Object arg;

    public Command() {
    }

    public Command(Object arg) {
        this.arg = arg;
    }

    public abstract void execute(VirtualMachine vm);
}

class CommandMove extends Command {
    public CommandMove() {
    }

    public void execute(VirtualMachine vm) {
        vm.move();
    }
}

class CommandWarp extends Command {
    public CommandWarp(float[] arg) {
        super(arg);
    }

    public void execute(VirtualMachine vm) {
        float[] point = (float[]) arg;
        vm.warp(point[0], point[1]);
    }
}

class CommandTurn extends Command {
    public CommandTurn(float degrees) {
        super(degrees);
    }

    public void execute(VirtualMachine vm) {
        float degrees = (Float) arg;
        vm.turn(degrees);
    }
}

class CommandPenUp extends Command {
    public CommandPenUp() {
    }

    public void execute(VirtualMachine vm) {
        vm.penUp();
    }
}

class CommandPenDown extends Command {
    public CommandPenDown() {
    }

    public void execute(VirtualMachine vm) {
        vm.penDown();
    }
}

class CommandSetSpeed extends Command {
    public CommandSetSpeed(int arg) {
        super(arg);
    }

    public void execute(VirtualMachine vm) {
        int speed = (Integer) arg;
        vm.setSpeed(speed);
    }
}
