package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HiddenLsCommandTest {

    @Test
    void execute() throws IOException {
        String input = "cd ..\\..\\Level 1";
        String [] parts = input.split(" ");
        File dir = new File(System.getProperty("user.dir"));
        Command cd = new CdCommand(dir,parts);
        dir = cd.execute();
        LsCommand ls = new LsCommand(dir,true);

        List<String> out = ls.getOutput();

        String comp = "";
        for (String s : out){
            comp+= s;
            comp+=" ";
        }
        assertEquals("Hidden.txt Level 2",comp.trim());
    }
}