package org.example;

import java.io.File;

public class LsCommand implements Command {
    private File dir;

    public LsCommand(File dir) {
        this.dir = dir;
    }

    @Override
    public File execute() {
        File[] files = dir.listFiles();
        for (File file : files) {System.out.println(file.getName());}
        return dir;
    }
}
