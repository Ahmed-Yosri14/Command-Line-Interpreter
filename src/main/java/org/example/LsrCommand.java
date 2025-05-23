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
    @Override
    public List<String> getOutput(){
        ArrayList<String>ret=new ArrayList<>();
        File []files = dir.listFiles();
        if (files != null){
            List<String> names = new ArrayList<>();
            int i = 0;
            for (File file : files){

                if ( file.isFile()){
                    if (file.isHidden())continue;
                    names.add(file.getName());
                }
                else if (!file.isHidden())names.add(file.getName());
            }
            Collections.sort(names, Collections.reverseOrder());
            for (String name : names){
                ret.add(name);
            }
        }
        return ret;
    }

    public File execute(){
        File []files = dir.listFiles();
        if (files != null){
            List<String> names = new ArrayList<>();
            int i = 0;
            for (File file : files){
                if ( file.isFile()){
                    if (file.isHidden())continue;
                    names.add(file.getName());
                }
                else if (!file.isHidden())names.add(file.getName());
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
