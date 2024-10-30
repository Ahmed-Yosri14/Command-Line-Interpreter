package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getOutput() throws IOException {
        List<String> output = new ArrayList<>();
        output.add(dir.getPath());
        return output;
    }
}
