package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LsrCommandTest {

    @Test
    void execute() throws IOException {
        String input = "cd ..\\..\\Level 1";
        String [] parts = input.split(" ");
        File dir = new File(System.getProperty("user.dir"));
        Command cd = new CdCommand(dir,parts);
        dir = cd.execute();
        LsrCommand lsr = new LsrCommand(dir);
        ArrayList<String> out = lsr.Get_Output();
        String comp = "";
        for (String s : out){
            comp+= s;
            comp+=" ";
        }
        assertEquals("Level 2",comp.trim());
    }

}