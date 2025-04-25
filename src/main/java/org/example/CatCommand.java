package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CatCommand implements Command {
    File dir;
    String fileName;

    CatCommand(File dir, String fileName) {
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
    public List<String> getOutput() throws IOException {
        List<String> ret = new ArrayList<>();
        fileName = fileName.trim();
        int greater = fileName.indexOf(">");
        int doubleGreater = fileName.indexOf(">>");
        String Content = "";

        if (greater == doubleGreater && greater == -1) {
            String[] currFiles = fileName.split(" ");
            for (var i : currFiles) {
                try {
                    Path filePath = resolvePath(i);
                    Content = Files.readString(filePath) + '\n';
                    ret.add(Content);
                } catch (IOException e) {
                    System.out.printf("cat: %s: No such file or directory%n", i);
                }
            }
        }
        return ret;
    }

    @Override
    public File execute() throws IOException {
        fileName = fileName.trim();
        int greater = fileName.indexOf(">");
        int doubleGreater = fileName.indexOf(">>");
        String Content = "";

        if (greater == doubleGreater && greater == -1) {
            String[] currFiles = fileName.split(" ");
            for (var i : currFiles) {
                try {
                    Path filePath = resolvePath(i);
                    Content = Files.readString(filePath) + '\n';
                    System.out.println(Content);
                } catch (IOException e) {
                    System.out.printf("cat: %s: No such file or directory%n", i);
                }
            }
        } else {
            int idx;
            if (greater == -1) idx = doubleGreater;
            else if (doubleGreater == -1) idx = greater;
            else idx = Math.min(greater, doubleGreater);

            String[] currFiles = fileName.substring(0, idx).split(" ");
            for (var i : currFiles) {
                try {
                    Path filePath = resolvePath(i);
                    Content += Files.readString(filePath) + '\n';
                } catch (IOException e) {
                    System.out.printf("cat: %s: No such file or directory%n", i);
                }
            }

            if (greater == doubleGreater) {
                DoubleGreaterCommand obj = new DoubleGreaterCommand(dir, fileName.substring(idx + 2), Content, "cat");
                obj.execute();
            } else {
                GreaterCommand obj = new GreaterCommand(dir, fileName.substring(idx + 1), Content, "cat");
                obj.execute();
            }
        }
        return dir;
    }
}