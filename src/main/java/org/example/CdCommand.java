package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CdCommand implements Command {
    private File dir;
    String line = "";
    boolean flag = false;

    public CdCommand(File dir, String[] input, boolean f, List<String> ls) {
        this.dir = dir;
        this.flag = f;

        if (!this.flag) {
            if (!input[0].equalsIgnoreCase("cd")) this.line += input[0];
            for (int i = 1; i < input.length; i++) {
                this.line += input[i];
                if (i < input.length - 1) this.line += " ";
            }
        } else {
            for (String s : ls) {
                this.line += s;
            }
        }
    }

    @Override
    public File execute() {
        line = line.trim();

        if (line.startsWith("~")) {
            String home = System.getProperty("user.home");
            line = home + line.substring(1);
        }

        File targetDir = new File(line);

        if (!targetDir.isAbsolute()) {
            targetDir = new File(dir, line);
        }

        try {
            targetDir = targetDir.getCanonicalFile();

            if (targetDir.exists() && targetDir.isDirectory()) {
                return targetDir;
            } else {
                System.out.println(targetDir.exists() ? "Not a directory" : "Directory not found");
                return this.dir;
            }
        } catch (IOException e) {
            System.out.println("Error resolving path: " + e.getMessage());
            return this.dir;
        }
    }

    @Override
    public List<String> getOutput() throws IOException {
        return new ArrayList<>();
    }
}