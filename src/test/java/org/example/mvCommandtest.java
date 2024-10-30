package org.example;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class mvCommandtest {
    @Test
    void Test1() throws IOException {
        String input = "Directory",Target="NewDirectory";
        String[] parts = {"mkdir", input};
        File dir = new File(System.getProperty("user.dir"));
        Command c;
        c = new MkdirCommand(dir, parts);
        dir = c.execute();
        Mv m=new Mv(dir,input,Target);
        dir=m.execute();
        LsCommand ls = new LsCommand(dir,false);
        List<String> out = ls.getOutput();
        assertTrue(out.contains(Target)&&!out.contains(input));
    }
    @Test
    void Test2() throws IOException {
        String input = "newtest",Target="..\\";
        String[] parts = {"mkdir", input};
        String[] parts2 = {"cd", Target};
        File dir = new File(System.getProperty("user.dir"));
        Command c;
        c = new MkdirCommand(dir, parts);
        dir = c.execute();
        Mv m=new Mv(dir,input,Target);
        dir=m.execute();
        CdCommand c1=new CdCommand(dir,parts2);
        dir=c1.execute();
        LsCommand ls = new LsCommand(dir,false);
        List<String> out = ls.getOutput();
        assertTrue(out.contains(input));
    }

}
