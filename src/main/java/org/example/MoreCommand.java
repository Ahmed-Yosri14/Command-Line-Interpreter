package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MoreCommand implements Command {
    private String []args ;
    private File dir;
    MoreCommand(String []args, File dir){
        this.args=args;
        this.dir=dir;
    }
    @Override
    public File execute() throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: more <filename>");
            return dir;
        }

        String fileName = args[1];
        File file = new File(dir.getPath() + "/" + fileName);

        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found.");
            return dir;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            Scanner scanner = new Scanner(System.in);

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;

                if (lineCount % 20 == 0) {
                    System.out.println("-- More -- (press Enter to continue)");
                    scanner.nextLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return dir;
    }
}
