package org.example;

import java.io.File;

public class LsCommand implements Command {
    private File dir;
    private boolean hidden;

    public LsCommand(File dir,boolean hidden) {
        this.dir = dir;
        this.hidden = hidden;
    }

    @Override
    public File execute() {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isHidden()) {
                if (hidden) {
                    System.out.println(file.getName());
                }
            }
            else System.out.println(file.getName());}
        return dir;
    }
}
