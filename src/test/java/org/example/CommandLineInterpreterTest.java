package org.example;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CommandLineInterpreterTest {

    @Test
    public void testCD() throws IOException {
        String input = "cd C:\\Users\\DELL\\Saved Games";
        String [] parts = input.split(" ");
        File dir = new File(System.getProperty("user.dir"));
        Command cd = new CdCommand(dir,parts);
        dir =cd.execute();
        PwdCommand pwd = new PwdCommand(dir);
        assertEquals("C:\\Users\\DELL\\Saved Games",pwd.Get_output());
    }
}
