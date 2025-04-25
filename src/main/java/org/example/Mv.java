package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Mv implements Command {
    File dir;
    File source, target;

    public Mv(File dir, String source, String target) {
        this.dir = dir;
        this.source = resolveFile(dir, source);
        this.target = resolveFile(dir, target);
    }

    private File resolveFile(File baseDir, String path) {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(baseDir, path);
        }
        return file;
    }

    @Override
    public File execute() throws IOException {
        if (!source.exists()) {
            System.out.println(source + " does not exist");
            return dir;
        }

        Path targetPath;
        if (target.isDirectory()) {
            targetPath = target.toPath().resolve(source.getName());
        } else {
            targetPath = target.toPath();
        }

        try {
            Files.move(source.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
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