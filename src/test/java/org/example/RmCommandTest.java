package org.example;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class RmCommandTest {
    @Test
    void Test1() throws IOException {
        String input = "NewDirectory";
        String[] parts = {"mkdir", input};
        File dir = new File(System.getProperty("user.dir"));
        Command c;
        c = new MkdirCommand(dir, parts);
        dir=c.execute();
        //assertTrue(out1.contains(input));
        RmdirCommand r=new RmdirCommand(dir,input);
        dir=r.execute();
       LsCommand ls = new LsCommand(dir,false);
        List<String> out = ls.getOutput();
        assertFalse(out.contains(input));
    }
}
