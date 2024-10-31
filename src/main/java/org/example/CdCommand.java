package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CdCommand implements Command {
    private File dir;
    String line="";
    boolean flag = false;
    public CdCommand(File dir, String [] input,boolean f, List<String> ls) {
        this.dir = dir;
        this.flag=f;
        if (!this.flag){

            if (!input[0].toLowerCase().equals("cd"))this.line+=input[0];
            for (int i = 1; i < input.length; i++) {
                this.line+=input[i];
                this.line+=" ";
            }
        }
        else {
            for (int i = 0; i < ls.size(); i++) {
                this.line+=ls.get(i);
            }
        }
    }


    @Override
    public File execute() {
        line = line.trim();
        String[] parts = line.split("\\\\");

        File targetDir = new File(line);
        if (targetDir.isAbsolute()) {
            if (targetDir.exists() && targetDir.isDirectory()) {
                return targetDir;
            } else {
                System.out.println(targetDir.exists() ? "Not a directory" : "Directory not found");
                return this.dir;
            }
        }
        File dir = this.dir;

        for (String part : parts) {
            if (part.equals("..")) {
                dir = dir.getParentFile();
            } else if (!part.isEmpty()) {
                File nextDir = new File(dir, part);
                if (nextDir.exists() && nextDir.isDirectory()) {
                    dir = nextDir;
                } else {
                    System.out.println(nextDir.exists() ? "Not a directory" : "Directory not found");
                    return this.dir;
                }
            }
        }
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return new ArrayList<>();
    }
}
