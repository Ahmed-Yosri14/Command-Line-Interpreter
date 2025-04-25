package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CatCommandTest {
    @Test
    void testCatCommand(@TempDir Path tempDir) throws IOException {
        String testContent = "Hello, world!";
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, testContent);

        File currentDir = tempDir.toFile();
        CatCommand cmd = new CatCommand(currentDir, "test.txt");

        String output = String.join("", cmd.getOutput()).trim();
        assertEquals(testContent, output);

        cmd.execute();
    }
}