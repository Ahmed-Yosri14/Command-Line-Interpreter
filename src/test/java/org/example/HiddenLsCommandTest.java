package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HiddenLsCommandTest {
    @Test
    void testHiddenFilesListing(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("visible.txt"));
        Files.createFile(tempDir.resolve(".hidden"));
        Files.createDirectory(tempDir.resolve(".hiddenDir"));

        File dir = tempDir.toFile();
        LsCommand ls = new LsCommand(dir, true); // show hidden

        List<String> out = ls.getOutput();

        assertTrue(out.contains("visible.txt"));
        assertTrue(out.contains(".hidden"));
        assertTrue(out.contains(".hiddenDir"));
    }
}