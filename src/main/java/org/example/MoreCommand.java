package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MoreCommand implements Command {
    private String[] args;
    private File dir;
    private boolean prev;
    private List<String> list;

    public MoreCommand(File dir, String[] args, boolean prev, List<String> list) {
        this.args = args;
        this.dir = dir;
        this.prev = prev;
        this.list = list;
    }

    @Override
    public File execute() throws IOException {
        if (prev) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0 ; i < list.size() ; i++) {
                System.out.println(list.get(i));
                if ((i+1)%20==0){
                    System.out.println("-- More -- (press Enter to continue)");
                    scanner.nextLine();
                }
            }
            return dir;
        }
        if (args.length < 2) {
            System.out.println("Usage: more <filename>");
            return dir;
        }

        String fileName = args[1];
        File file = new File(dir.getPath().trim() + File.separator + fileName);

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

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}
