package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RmdirCommandTest {

    @Test
    void execute() throws IOException {
        File dir = new File ( System.getProperty("user.dir"));
        String newDirectory = "NeWDir";
        String[] line = { "mkdir", newDirectory };
        Command mkdir = new MkdirCommand(dir, line);
        dir = mkdir.execute();
        Command rmdir = new RmdirCommand(dir, newDirectory);
        dir = rmdir.execute();
        Command ls = new LsCommand(dir , false);
        List<String> lst = ls.getOutput();
        assertFalse( lst.contains(newDirectory));
    }
}