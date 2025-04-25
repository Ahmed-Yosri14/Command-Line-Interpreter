package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class RmCommandTest {
    @Test
    void testFileDeletion(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "Test content");

        File currentDir = tempDir.toFile();
        rmCommand rm = new rmCommand(currentDir, "test.txt");
        rm.execute();

        assertFalse(Files.exists(testFile));
    }

    @Test
    void testDirectoryDeletion(@TempDir Path tempDir) throws IOException {
        Path testDir = tempDir.resolve("testdir");
        Files.createDirectory(testDir);
        Files.writeString(testDir.resolve("file.txt"), "Test content");

        File currentDir = tempDir.toFile();
        rmCommand rm = new rmCommand(currentDir, "testdir");
        rm.execute();

        assertFalse(Files.exists(testDir));
    }
}