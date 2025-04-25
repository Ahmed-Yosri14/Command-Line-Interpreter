package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GreaterCommandTest {
    @Test
    void testGreaterCommand(@TempDir Path tempDir) throws IOException {
        String sourceContent = "Test content\n";
        Path sourceFile = tempDir.resolve("source.txt");
        Files.writeString(sourceFile, sourceContent);

        Path destFile = tempDir.resolve("dest.txt");

        File currentDir = tempDir.toFile();

        GreaterCommand cmd = new GreaterCommand(
                currentDir,
                "source.txt>dest.txt",
                sourceContent,
                "test"
        );
        cmd.execute();

        String actualContent = Files.readString(destFile);
        assertEquals(sourceContent.trim(), actualContent.trim());
    }
}