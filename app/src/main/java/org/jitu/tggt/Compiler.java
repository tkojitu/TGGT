package org.jitu.tggt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Compiler {
    private StringBuffer buffer = new StringBuffer();

    public Program compile(File file) throws IOException, SyntaxErrorException {
        Program program = new Program();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            compileLine(program, line.trim());
        }
        compileExit(program);
        return program;
    }

    private void compileLine(Program program, String line)
            throws SyntaxErrorException {
        if (line.isEmpty()) {
            return;
        }
        char ch = line.charAt(0);
        switch (ch) {
        case 'd':
        case 'D':
            compilePenDown(program, line);
            break;
        case 'm':
        case 'M':
            compileMove(program, line);
            break;
        case 'n':
        case 'N':
            compileName(program, line);
            break;
        case 's':
        case 'S':
            compileSetSpeed(program, line);
            break;
        case 't':
        case 'T':
            compileTurn(program, line);
            break;
        case 'u':
        case 'U':
            compilePenUp(program, line);
            break;
        case 'w':
        case 'W':
            compileWarp(program, line);
            break;
        case '#':
        default:
            break;
        }
    }

    private void compilePenDown(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("d") && !token.equals("D")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        program.addCommand(new CommandPenDown());
    }

    private void compilePenUp(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("u") && !token.equals("U")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        program.addCommand(new CommandPenUp());
    }

    private void compileMove(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("m") && !token.equals("M")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        if (!tokens.hasMoreTokens()) {
            throw new SyntaxErrorException("move command needs an integer");
        }
        try {
            int times = Integer.parseInt(tokens.nextToken());
            if (times <= 0) {
                return;
            }
            for (int i = 0; i < times; ++i) {
                program.addCommand(new CommandMove());
            }
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("move command needs an integer");
        }
    }

    private void compileSetSpeed(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("s") && !token.equals("S")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        if (!tokens.hasMoreTokens()) {
            throw new SyntaxErrorException("setSpeed command needs an integer");
        }
        try {
            int speed = Integer.parseInt(tokens.nextToken());
            if (speed <= 0) {
                throw new SyntaxErrorException("setSpeed command needs a positive integer");
            }
            program.addCommand(new CommandSetSpeed(speed));
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("setSpeed command needs an integer");
        }
    }

    private void compileTurn(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("t") && !token.equals("T")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        if (!tokens.hasMoreTokens()) {
            throw new SyntaxErrorException("turn command needs degrees");
        }
        try {
            Float degrees = Float.parseFloat(tokens.nextToken());
            program.addCommand(new CommandTurn(degrees));
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("move command needs degrees");
        }
    }

    private void compileWarp(Program program, String line)
            throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("w") && !token.equals("W")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        if (tokens.countTokens() < 2) {
            throw new SyntaxErrorException("warp command needs x y");
        }
        try {
            float[] point = new float[2];
            point[0] = Float.parseFloat(tokens.nextToken());
            point[1] = Float.parseFloat(tokens.nextToken());
            program.addCommand(new CommandWarp(point));
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("warp command needs x y");
        }
    }

    private void compileName(Program program, String line) throws SyntaxErrorException {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if (!token.equals("n") && !token.equals("N")) {
            throw new SyntaxErrorException("illegal command: " + token);
        }
        if (!tokens.hasMoreTokens()) {
            throw new SyntaxErrorException("name command needs a name");
        }
        token = tokens.nextToken();
        program.addCommand(new CommandName(token));
    }

    private void compileExit(Program program) {
        Command cmd = new CommandExit();
        program.addCommand(cmd);
    }
}
