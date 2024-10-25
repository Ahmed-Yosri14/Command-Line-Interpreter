package org.example;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    private File dir;

    public CLI() {
        dir = new File(System.getProperty("user.dir"));
    }


    public void executeCommand(String input) {
        String[] parts = input.split(" ");
        Command command = null;
        String commandName = parts[0].toLowerCase();
        switch (commandName) {
            case "cd":
                command = new CdCommand(dir,parts[1]);
                break;
            case "ls":
                if ( parts.length == 1)
                    command = new LsCommand(dir);
                else if (parts[1].equalsIgnoreCase("-r")){
                    command = new LsrCommand(dir);
                }
                break;
            case "pwd":
                command = new PwdCommand(dir);
                break;
            case "mkdir":
                command = new MkdirCommand(dir, parts[1]);
                break;
            case "rmdir":
                command = new RmdirCommand(dir, parts[1]);
                break;

            default:
                if (parts.length==1){
                    command = new CdCommand(dir,parts[0]);
                }
                else {
                    System.out.println("Invalid command");
                    return;
                }
        }
        dir=command.execute();
    }
}

