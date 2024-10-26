package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
public class Mv implements Command {
   File dir;
    String source,target;
    public Mv(File dir,String source,String target){
        this.dir=dir;
        this.source=source;;
        this.target=target;
    }
    @Override
    public File execute() throws IOException {
        Files.move(Path.of(source), Path.of(target));
        return dir;
    }
}
