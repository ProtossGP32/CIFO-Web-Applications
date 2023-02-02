package org.labse03part1.ui;

import java.util.Scanner;

public class UserInterface {

    public static void start() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            // Show the available options of Library management
            menu();
            break;
        }
    }

    private static void menu() {
        StringBuilder menuText = new StringBuilder();
        menuText.append("1 - Add Book\n");
        menuText.append("2 - Delete Book\n");
        menuText.append("3 - Get one Book\n");
        menuText.append("4 - Get all Books\n");
        menuText.append("5 - Update Book\n");
        menuText.append("6 - Quit\n");
        System.out.println(menuText);
    }
}
