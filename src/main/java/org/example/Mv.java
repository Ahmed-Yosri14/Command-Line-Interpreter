package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

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
        }
        Path targetPath = target.isDirectory() ? target.toPath().resolve(source.getName()) : target.toPath();
        try {
            Files.move(source.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved " + source + " to " + targetPath);
        } catch (IOException e) {
            System.out.println("Failed to move " + source + " to " + targetPath + ": " + e.getMessage());
        }
        return dir;

    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}
