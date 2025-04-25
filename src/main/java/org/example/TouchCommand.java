package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TouchCommand implements Command {
    File dir;
    String fileName;

    TouchCommand(File dir, String fileName) {
        this.dir = dir;
        this.fileName = fileName;
    }

    private Path resolvePath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename);
        }
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    @Override
    public File execute() throws IOException {
        Path filePath = resolvePath(fileName);
        try {
            Files.createFile(filePath);
            System.out.println("File created at: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            try {
                Files.setLastModifiedTime(filePath, java.nio.file.attribute.FileTime.fromMillis(System.currentTimeMillis()));
            } catch (IOException ex) {
                System.out.println("Error touching file: " + ex.getMessage());
            }
        }
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}