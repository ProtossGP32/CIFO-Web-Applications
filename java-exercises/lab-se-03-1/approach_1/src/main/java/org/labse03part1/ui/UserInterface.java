package org.labse03part1.ui;

import org.labse03part1.logic.AuthorManager;
import org.labse03part1.logic.BookManager;
import org.labse03part1.logic.BorrowManager;
import org.labse03part1.utils.InterfaceUtils;

import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {

    private enum userInterfaceOptionsEnum {
        AUTHOR_MANAGER("Manage authors") {
            @Override
            void start(Scanner reader) {
                AuthorManager.start(reader);
            }
        },
        BOOK_MANAGER("Manage books") {
            @Override
            void start(Scanner reader) {
                BookManager.start(reader);
            }
        },
        BORROW_MANAGER("Manage borrows") {
            @Override
            void start(Scanner reader) {
                BorrowManager.start(reader);
            }
        };

        abstract void start(Scanner reader);
        private final String action;
        userInterfaceOptionsEnum(String action) {
            this.action = action;
        }

        private String getAction() {
            return this.action;
        }

        public static Stream<userInterfaceOptionsEnum> stream() {
            return Stream.of(userInterfaceOptionsEnum.values());
        }
    }
    public static void start() {

        // For testing purposes we are going to initialize fake authors and books
        AuthorManager.initializeRandomActors();
        BookManager.initializeRandomBooks();

        // Initialize local variables
        Scanner reader = new Scanner(System.in);
        String optionStr;

        while (true) {
            // Show the available options of Library management
            menu();
            optionStr = InterfaceUtils.askString(reader, "- Select option ('Quit' to exit): ");
            if (optionStr.equals("Quit")) {
                break;
            }
            executeOption(reader, optionStr);
            // Blank line to separate executions
            System.out.println();
        }
        System.out.println("Bye!");
    }

    private static void menu() {
        System.out.println("Welcome to the Library!");
        printOptions();
    }

    private static void printOptions() {
        // Print all the available Borrow options
        System.out.println("[Library] Available options:");
        userInterfaceOptionsEnum.stream()
                .map(userInterfaceOptionsEnum::getAction)
                .forEach(System.out::println);
    }

    private static userInterfaceOptionsEnum getOption(String action) {
        for (userInterfaceOptionsEnum option : userInterfaceOptionsEnum.values()) {
            if (option.getAction().equals(action)) {
                return option;
            }
        }
        return null;
    }

    private static void executeOption(Scanner reader, String input) {
        // Convert selected option into enum value
        userInterfaceOptionsEnum option = getOption(input);
        // Execute the corresponding manager
        if (option != null) {
            // Each enum option has its own start method
            option.start(reader);
        } else {
            System.out.println("Invalid option!");
        }
    }
}
