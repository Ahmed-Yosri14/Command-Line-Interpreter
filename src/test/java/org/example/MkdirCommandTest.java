package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MkdirCommandTest {

    @Test
    void execute() throws IOException {// mkdir abdo
        File dir = new File (System.getProperty("user.dir"));
        String newDirectory = "NewDerectory";
        String[] input = {"mkdir",newDirectory };
        Command mkdir = new MkdirCommand(dir , input);
        dir = mkdir.execute();
        Command ls = new LsCommand(dir,false);
        List<String> lst =  ls.getOutput();
        assert(lst.contains(newDirectory));
    }
}