package org.example;
import java.io.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    private File dir;

    public CLI() {
        dir = new File(System.getProperty("user.dir"));
    }


    public void executeCommand(String input) throws IOException {
        String[] parts = input.split(" ");
        Command command = null ;


        String commandName = parts[0].toLowerCase();
        switch (commandName) {
            case "cd":
                if (parts.length==1){
                    command= new PwdCommand(dir);
                }
                else command = new CdCommand(dir,parts[1]);
                break;
            case "ls":
                command = new LsCommand(dir);
                break;
            case "pwd":
                command = new PwdCommand(dir);
                break;
            case "touch":
                command = new TouchCommand(dir,parts[1]);
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

