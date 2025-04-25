package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class TouchCommandTest {
    @Test
    void testFileCreation(@TempDir Path tempDir) throws IOException {
        File currentDir = tempDir.toFile();
        String fileName = "newfile.txt";

        TouchCommand cmd = new TouchCommand(currentDir, fileName);
        cmd.execute();

        Path createdFile = tempDir.resolve(fileName);
        assertTrue(Files.exists(createdFile));
    }

    @Test
    void testTimestampUpdate(@TempDir Path tempDir) throws IOException, InterruptedException {
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "Test content");
        FileTime oldTime = FileTime.from(Instant.now().minusSeconds(60));
        Files.setLastModifiedTime(testFile, oldTime);

        File currentDir = tempDir.toFile();
        TouchCommand cmd = new TouchCommand(currentDir, "test.txt");
        cmd.execute();

        FileTime newTime = Files.getLastModifiedTime(testFile);
        assertTrue(newTime.toInstant().isAfter(oldTime.toInstant()));
    }
}