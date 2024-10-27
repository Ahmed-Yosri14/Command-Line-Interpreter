package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class LsrCommand implements Command{
    File dir;
    LsrCommand(File dir){
        this.dir = dir;
    }

    public File execute(){
        File []files = dir.listFiles();
        if (files != null){
            List<String> names = new ArrayList<>();
            int i = 0;
            for (File file : files){
                if ( file.isFile()){
                    names.add(file.getName()+ " [FILE]");
                }
                else names.add(file.getName() + " [DIR]");
            }
            Collections.sort(names, Collections.reverseOrder());
            for (String name : names){
                System.out.println(name);
            }
        }
        else {
            System.out.println("The Directory is empty");
        }
        return dir;
    }
}
