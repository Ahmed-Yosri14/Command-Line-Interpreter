package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MkdirCommandTest {
    @Test
    void testMkdirCommand(@TempDir Path tempDir) throws IOException {
        File currentDir = tempDir.toFile();
        String newDirName = "newdir";
        String[] input = {"mkdir", newDirName};

        Command mkdir = new MkdirCommand(currentDir, input);
        File newDir = mkdir.execute();

        Path createdDir = tempDir.resolve(newDirName);
        assertTrue(Files.exists(createdDir));
        assertTrue(Files.isDirectory(createdDir));

        String nestedDir = "parent/child";
        input = new String[]{"mkdir", nestedDir};
        mkdir = new MkdirCommand(currentDir, input);
        newDir = mkdir.execute();

        Path nestedPath = tempDir.resolve("parent/child");
        assertTrue(Files.exists(nestedPath));
        assertTrue(Files.isDirectory(nestedPath));
    }
}