package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class rmCommand implements Command {
    File dir;
    File Targetfile;

    public rmCommand(File dir, String filename) {
        this.dir = dir;
        this.Targetfile = resolveFile(dir, filename);
    }

    private File resolveFile(File baseDir, String path) {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(baseDir, path);
        }
        return file;
    }

    private boolean recdeletefile(File temp) throws IOException {
        if (temp.isDirectory()) {
            File[] files = temp.listFiles();
            if (files != null) {
                for (File child : files) {
                    recdeletefile(child);
                }
            }
        }
        return Files.deleteIfExists(temp.toPath());
    }

    @Override
    public File execute() throws IOException {
        if (!Targetfile.exists()) {
            System.out.println("File does not exist: " + Targetfile.getAbsolutePath());
            return dir;
        }

        try {
            if (recdeletefile(Targetfile)) {
                System.out.println(Targetfile.getAbsolutePath() + " is deleted");
            } else {
                System.out.println("Delete failed for: " + Targetfile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}