package org.example;

import java.io.File;

public class MkdirCommand implements Command{
    File dir ;
    String[] line;
    MkdirCommand(File dir , String[] line ){
        this.dir = dir;
        this.line = line ;
    }
    @Override
    public File execute(){
        File currentdir = dir;
        for (int i = 1; i < line.length; i++) {
            dir = currentdir;
            String path = line[i];
            File newdir = new File(dir, path);
            if (newdir.mkdirs()) {
                System.out.println("Directory created: " + newdir.getPath());
            } else if (newdir.exists()) {
                System.out.println("Directory already exists: " + newdir.getPath());
            } else {
                System.out.println("Failed to create directory: " + newdir.getPath());
            }
        }
        return currentdir;
    }
}
