package org.example;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LsCommandTest {

    @Test
    void execute() throws IOException {
        File dir = new File(System.getProperty("user.dir"));
        LsCommand ls = new LsCommand(dir,false);

        List<String> out = ls.getOutput();

        String comp = "";
        for (String s : out){
            comp+= s;
            comp+=" ";
        }
        String out2 =  ".gitignore " + ".idea " + "1.txt " + "c.txt " + "Command-Line-Interpreter " +
                "from.txt " + "IdeaProjects " + "pom.xml " + "PP.txt " + "src " + "target " + "to.txt ";

        Assert.assertEquals(comp.trim(),out2.trim());
    }


}