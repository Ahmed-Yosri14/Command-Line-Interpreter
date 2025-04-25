package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LsrCommandTest {
    @Test
    void testReverseListing(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));
        Files.createDirectory(tempDir.resolve("subdir"));

        File dir = tempDir.toFile();
        LsrCommand lsr = new LsrCommand(dir);

        List<String> out = lsr.getOutput();

        assertTrue(out.indexOf("file1.txt") > out.indexOf("file2.txt") ||
                out.indexOf("file2.txt") > out.indexOf("file1.txt"));
        assertTrue(out.contains("subdir"));
    }
}