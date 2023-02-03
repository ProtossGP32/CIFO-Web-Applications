package org.labse03part1.ui;

import org.labse03part1.logic.BorrowManager;
import org.labse03part1.utils.InterfaceUtils;

import java.util.Scanner;

public class UserInterface {

    public static void start() {

        Scanner reader = new Scanner(System.in);
        // Show the available options of Library management
        menu();
        BorrowManager.printOptions();
        String option;
        while (true) {
            option = InterfaceUtils.askString(reader, "- Select option:");
            System.out.println("Selected option is " + option);
            break;
        }
        System.out.println("Bye!");
    }

    private static void menu() {
        System.out.println("Welcome to the Library!");
        // TODO: Create a general menu for other Library options
    }
}
