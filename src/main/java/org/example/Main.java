package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            input =input.trim();
            if (input.equals("..\\")){
                input= "cd ..\\";
            }
            cli.executeCommand(input);
        }
    }
}
