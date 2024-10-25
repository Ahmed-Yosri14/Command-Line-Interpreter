package org.example;

import java.io.File;

public class MkdirCommand implements Command{
    File dir ;
    String line;
    MkdirCommand(File dir , String line ){
        this.dir = dir;
        this.line = line ;
    }
    @Override
    public File execute(){
        File newDir = new File(dir, line);
        if ( newDir.mkdir()){
            System.out.println("Directory created: "+ newDir.getAbsolutePath());
        }
        else {
            System.out.println("Directory is already exists: "+newDir.getAbsolutePath());
        }
        return dir;
    }
}
