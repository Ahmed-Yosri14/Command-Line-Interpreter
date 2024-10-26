package org.example;

import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    private File dir;

    public CLI() {
        dir = new File(System.getProperty("user.dir"));
    }


    public void executeCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        Command command = null;
        String commandName = parts[0].toLowerCase();
        switch (commandName) {
            case "cd":
                if (parts.length==1){
                    command= new PwdCommand(dir);
                }
                else command = new CdCommand(dir,parts);
                break;
            case "ls":
                if ( parts.length == 1)
                    command = new LsCommand(dir,false);
                else if (parts[1].equalsIgnoreCase("-r")){
                    command = new LsrCommand(dir);
                }
                else if (parts[1].equalsIgnoreCase("-a")){
                    command = new LsCommand(dir,true);
                }
                break;
            case "touch":
                command = new TouchCommand(dir,parts[1]);
            case "pwd":
                command = new PwdCommand(dir);
                break;
            case "mkdir":
                command = new MkdirCommand(dir, parts[1]);
                break;
            case "rmdir":
                command = new RmdirCommand(dir, parts[1]);
                break;
            case "mv":
                command=new Mv(dir,parts[1],parts[2] );
            default:
                if (parts.length==1){
                    command = new CdCommand(dir,parts);
                }
                else {
                    System.out.println("Invalid command");
                    return;
                }
        }
        assert command != null;
        dir=command.execute();
    }
}

