package org.example;

import java.io.File;

public class RmdirCommand implements Command{
    File dir;
    String line ;
    RmdirCommand(File dir , String line ){
        this.dir =dir;
        this.line = line;
    }
    @Override
    public File execute(){
        File remDir = new File(dir, line);
        if ( remDir.isDirectory() && remDir.exists()){
            if (remDir.delete()) {
                System.out.println("Directory removed: " + remDir.getAbsolutePath());
            } else {
                System.out.println("Failed to remove directory (make sure it is empty): " + remDir.getAbsolutePath());
            }
        }
        else {
            System.out.println("Directory is not exist!" + remDir.getAbsolutePath());
        }
        return dir;
    }
}
