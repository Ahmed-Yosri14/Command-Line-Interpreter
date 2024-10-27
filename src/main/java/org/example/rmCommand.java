package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class rmCommand implements Command{
    File dir,Targetfile;

    public rmCommand(File dir,String filename){
        this.dir=dir;
        this.Targetfile=new File(dir,filename);
    }
    private boolean recdeletefile(File temp){
        if (Targetfile.isDirectory()){
            for(File temp2: Objects.requireNonNull(Targetfile.listFiles())){
                recdeletefile(temp2);
            }
        }
        return temp.delete();
    }
    @Override
    public File execute() throws IOException {
        if (!Targetfile.exists()){
            System.out.println("File does not exists"+Targetfile);
        }
        if (recdeletefile(Targetfile)){
            System.out.println(Targetfile+" is deleted");
        }
        else
            System.out.println("Delete is failed");
        return dir;
    }
}
