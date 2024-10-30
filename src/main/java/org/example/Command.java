package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Command {
    File execute() throws IOException;
    List<String> getOutput() throws IOException; // Change to List<String>
}
