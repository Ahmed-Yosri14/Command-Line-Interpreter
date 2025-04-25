package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CdCommandTest {
    @Test
    void testCdCommand(@TempDir Path tempDir) throws IOException {
        Path subDir = tempDir.resolve("testdir");
        Files.createDirectory(subDir);

        File originalDir = tempDir.toFile();
        String[] parts = {"cd", "testdir"};

        Command cd = new CdCommand(originalDir, parts, false, new ArrayList<>());
        File newDir = cd.execute();
        assertEquals(subDir.toFile(), newDir);

        parts = new String[]{"cd", ".."};
        cd = new CdCommand(newDir, parts, false, new ArrayList<>());
        File parentDir = cd.execute();
        assertEquals(originalDir, parentDir);
    }
}