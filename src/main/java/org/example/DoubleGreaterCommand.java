package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DoubleGreaterCommand implements Command {
    File dir;
    String fileName, Content, baseCmd;

    public DoubleGreaterCommand(File dir, String fileName, String Content, String baseCmd) {
        this.dir = dir;
        this.fileName = fileName;
        this.Content = Content;
        this.baseCmd = baseCmd;
    }

    private Path resolvePath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename);
        }
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    public File execute() throws IOException {
        fileName = fileName.trim();
        int greater = fileName.indexOf(">");
        int doubleGreater = fileName.indexOf(">>");

        if (greater == doubleGreater && greater == -1) {
            String[] currFiles = fileName.split(" ");
            for (var i : currFiles) {
                try (FileWriter writer = new FileWriter(resolvePath(i).toFile(), true)) {
                    writer.write(Content);
                } catch (IOException e) {
                    System.out.printf("%s: %s: No such file or directory%n", baseCmd, i);
                }
            }
        } else {
            int idx;
            if (greater == -1) idx = doubleGreater;
            else if (doubleGreater == -1) idx = greater;
            else idx = Math.min(greater, doubleGreater);

            String[] currFiles = fileName.substring(0, idx).split(" ");
            for (var i : currFiles) {
                try (FileWriter writer = new FileWriter(resolvePath(i).toFile(), true)) {
                    writer.write(Content);
                } catch (IOException e) {
                    System.out.printf("%s: %s: No such file or directory%n", baseCmd, i);
                }
            }

            Content = "";
            for (var i : currFiles) {
                try {
                    Content += Files.readString(resolvePath(i)) + '\n';
                } catch (IOException e) {
                }
            }

            if (greater == doubleGreater) {
                DoubleGreaterCommand obj = new DoubleGreaterCommand(dir, fileName.substring(idx + 2), Content, baseCmd);
                obj.execute();
            } else {
                GreaterCommand obj = new GreaterCommand(dir, fileName.substring(idx + 1), Content, baseCmd);
                obj.execute();
            }
        }
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}