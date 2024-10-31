package org.example;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class LsrCommandTest {
    @Test
    void execute() throws IOException {
        File dir = new File(System.getProperty("user.dir"));
        LsrCommand lsr = new LsrCommand(dir);

        List<String> out = lsr.getOutput();
        String comp = "";
        for (String s : out){
            comp+= s;
            comp+=" ";
        }
        String out2 =  "to.txt " + "target " + "src " + "pom.xml " + "from.txt " + "c.txt "+"a.txt " + "PP.txt " + "IdeaProjects " +
                "Command-Line-Interpreter " + "1.txt " + ".idea " + ".gitignore";
        Assert.assertEquals(comp.trim(),out2.trim());
    }
}