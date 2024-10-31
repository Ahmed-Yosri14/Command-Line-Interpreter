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
    public String get_dir(){
        return dir.getAbsolutePath();
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

        // Check if "help" is requested for each command
        boolean isHelpRequested = parts.length > 1 && parts[1].equalsIgnoreCase("help");

        if (isHelpRequested) {
            switch (commandName) {
                case "cd":
                    return new HelpCommand("cd [directory] - Changes the current directory to the specified path.",dir);
                case "ls":
                    return new HelpCommand("ls [-r|-a] - Lists directory contents. Use -r for recursive, -a to show hidden files.",dir);
                case "touch":
                    return new HelpCommand("touch [filename] - Creates a new file with the specified name.",dir);
                case "pwd":
                    return new HelpCommand("pwd - Prints the current working directory path.",dir);
                case "mkdir":
                    return new HelpCommand("mkdir [directory] - Creates a new directory with the specified name.",dir);
                case "rmdir":
                    return new HelpCommand("rmdir [directory] - Deletes the specified directory if empty.",dir);
                case "cat":
                    return new HelpCommand("cat [file] - Outputs the contents of the specified file.",dir);
                case "more":
                    return new HelpCommand("more [file] - Displays file contents one screen at a time.",dir);
                case "mv":
                    return new HelpCommand("mv [source] [destination] - Moves or renames files or directories.",dir);
                case "rm":
                    return new HelpCommand("rm [file] - Deletes the specified file.",dir);
                case "exit":
                    return new HelpCommand("exit - Exits the program.",dir);
                default:
                    return new HelpCommand("Unknown command. Type [command] help for available commands.",dir);
            }
        }
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
