package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CLIPipelineTest {

    @Test
    void executeCommand() throws IOException {
        CLI cli = new CLI();
        cli.executeCommand("cat a.txt | cd");
        assertEquals(cli.get_dir(),"C:\\");
    }
}