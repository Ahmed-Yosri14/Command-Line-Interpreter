package org.example;

import java.io.File;
import java.io.IOException;

public interface Command {
    File execute() throws IOException;
}
