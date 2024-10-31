package org.example;

import org.example.Command;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelpCommand implements Command {
    private final String helpMessage;
    File dir;
    public HelpCommand(String helpMessage,File dir) {
        this.helpMessage = helpMessage;
        this.dir = dir;
    }

    @Override
    public File execute() {
        System.out.println(helpMessage);
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}
