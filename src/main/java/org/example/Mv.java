package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
public class Mv implements Command {
   File dir;
    File source,target;
    public Mv(File dir,String source,String target){
        this.dir=dir;
        this.source=new File(dir,source);
        this.target=new File(dir,target);
    }
    @Override
    public File execute() throws IOException {
        if (!source.exists()){
            System.out.println(source+" does not exits");
        } else if (source.renameTo(target)) {
            System.out.println("move "+source+" to "+target);
        }
        return dir;

    }
}
