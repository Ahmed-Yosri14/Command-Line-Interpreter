package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MvCommandTest {
    @Test
    void testFileMove(@TempDir Path tempDir) throws IOException {
        Path sourceFile = tempDir.resolve("source.txt");
        Files.writeString(sourceFile, "Test content");

        File currentDir = tempDir.toFile();
        Mv mv = new Mv(currentDir, "source.txt", "dest.txt");
        mv.execute();

        assertFalse(Files.exists(sourceFile));
        assertTrue(Files.exists(tempDir.resolve("dest.txt")));
    }

    @Test
    void testDirectoryMove(@TempDir Path tempDir) throws IOException {
        Path sourceDir = tempDir.resolve("source");
        Files.createDirectory(sourceDir);

        File currentDir = tempDir.toFile();
        Mv mv = new Mv(currentDir, "source", "dest");
        mv.execute();

        assertFalse(Files.exists(sourceDir));
        assertTrue(Files.exists(tempDir.resolve("dest")));
    }
}