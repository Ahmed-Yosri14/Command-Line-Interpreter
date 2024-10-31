package org.example;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CdCommandTest {

    @Test
    void execute() throws IOException {
        String input = "cd src";
        String prev = "";
        File dir = new File(System.getProperty("user.dir"));
        prev = dir.getAbsolutePath().trim();
        String [] parts = input.split(" ");
        Command cd = new CdCommand(dir,parts);
        dir= cd.execute();
        PwdCommand pwd = new PwdCommand(dir);
        String ans = pwd.getOutput().getFirst().trim();
        Assert.assertEquals(prev+"\\"+"src", ans);

    }
}