package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CLI {
    private File dir;
    private List<String> previousOutput=new ArrayList<>() ;
    public CLI() {
        dir = new File(System.getProperty("user.dir"));
    }

    public void executeCommand(String input) throws IOException {
        String[] commands = input.split("\\|");


        for (int i = 0; i < commands.length; i++) {
            String commandString = commands[i].trim();
            String[] parts = commandString.split(" ");
            Command command = parseCommand(parts, i);

            if (command == null) {
                System.out.println("Invalid command: " + parts[0]);
                return;
            }

            if (i < commands.length - 1) {
                dir = command.execute();
                previousOutput = command.getOutput();
            } else {

                dir = command.execute();
            }
        }
    }

    private Command parseCommand(String[] parts, int idx) {
        String commandName = parts[0].toLowerCase();
        switch (commandName) {
            case "cd":
                if (idx==0){
                    return new CdCommand(dir, parts,false,this.previousOutput);
                }
                else return new CdCommand(dir,parts,true,this.previousOutput);
            case "ls":
                return parts.length == 1 ? new LsCommand(dir, false) :
                        parts[1].equalsIgnoreCase("-r") ? new LsrCommand(dir) :
                                parts[1].equalsIgnoreCase("-a") ? new LsCommand(dir, true) : null;
            case "touch":
                return new TouchCommand(dir, parts[1]);
            case "pwd":
                return new PwdCommand(dir);
            case "mkdir":
                return new MkdirCommand(dir, parts);
            case "rmdir":
                return new RmdirCommand(dir, parts[1]);
            case "cat":
                return new CatCommand(dir, parts[1]);
            case "more":
                if (idx==0){
                return new MoreCommand(dir, parts,false,this.previousOutput);}
                else return new MoreCommand(dir,parts,true,this.previousOutput);
            case "mv":
                return new Mv(dir, parts[1], parts[2]);
            case "rm":
                return new rmCommand(dir, parts[1]);
            default:
                return null;
        }
    }
}
