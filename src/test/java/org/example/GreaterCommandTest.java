package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class GreaterCommandTest {
    @Test
    void execute() throws IOException {
        File CurrentDir=new File(System.getProperty("user.dir"));
        String from= Files.readString(Paths.get("from.txt"));
        CatCommand cmd=new CatCommand(CurrentDir,"from.txt>to.txt");
        cmd.execute();
        String to=Files.readString(Paths.get("to.txt"));
        to=to.substring(0,Math.max(to.length()-1,0));
        assertEquals(from,to);

    }
}
