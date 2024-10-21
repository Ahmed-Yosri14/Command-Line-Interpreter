package org.example;

import java.io.File;

public class PwdCommand implements Command {
    private File dir;

    public PwdCommand(File dir) {
        this.dir = dir;
    }

    @Override
    public File execute() {
        System.out.println(dir.getPath());
        return dir;
    }
}
