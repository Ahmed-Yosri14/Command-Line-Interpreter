package org.example;

import java.io.File;

public class CdCommand implements Command {
    private File dir;
    String line="";
    public CdCommand(File dir, String [] input) {
        this.dir = dir;
        if (!input[0].toLowerCase().equals("cd"))this.line+=input[0];
        for (int i = 1; i < input.length; i++) {
            this.line+=input[i];
            this.line+=" ";
        }
    }

    @Override
    public File execute() {
        line.trim();
        String path = "";
        for (int i = 0; i < line.length(); i++){
            path+=line.charAt(i);
            if (line.charAt(i)=='\\'){
                path =path.trim();
                File next = new File(dir,path);
                if (path.equals("..\\")){
                    next=dir.getParentFile();
                }
                path="";
                if (next.exists()&&next.isDirectory()){
                    dir=next;
                }
                else {
                    if (!next.exists()){
                        System.out.println("Directory not found");
                    }
                    else System.out.println("Not a directory");
                    return dir;
                }
            }
        }
        if (!path.isEmpty()) {
            path = path.trim();
            File next = new File(dir, path);
            if (path.equals( "..")) {
                next = dir.getParentFile();
            }
            if (next.exists() && next.isDirectory()) {
                dir = next;
            } else {
                System.out.println("No such directory");
            }
        }
        return dir;
    }
}
