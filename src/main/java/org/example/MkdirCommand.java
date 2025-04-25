package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MkdirCommand implements Command {
    File dir;
    String[] line;

    MkdirCommand(File dir, String[] line) {
        this.dir = dir;
        this.line = line;
    }

    private File resolvePath(String path) {
        if (Paths.get(path).isAbsolute()) {
            return new File(path);
        }
        return new File(dir, path);
    }

    @Override
    public File execute() {
        File currentdir = dir;
        for (int i = 1; i < line.length; i++) {
            File newdir = resolvePath(line[i]);
            try {
                if (newdir.mkdirs()) {
                    System.out.println("Directory created: " + newdir.getCanonicalPath());
                } else if (newdir.exists()) {
                    System.out.println("Directory already exists: " + newdir.getCanonicalPath());
                } else {
                    System.out.println("Failed to create directory: " + newdir.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
        return currentdir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}