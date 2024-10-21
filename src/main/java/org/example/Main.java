package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CLI cli = new CLI();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            cli.executeCommand(input);
        }
    }
}
