package org.jitu.tggt;

import java.util.ArrayList;

public class Program {
    private ArrayList<Command> commands = new ArrayList<Command>();

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public Command getCommand(int index) {
        if (index < 0 || commands.size() <= index) {
            return null;
        }
        return commands.get(index);
    }
}
