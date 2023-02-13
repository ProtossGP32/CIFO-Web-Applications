package org.labse03part1.logic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.labse03part1.domain.Borrow;
import org.labse03part1.utils.InterfaceUtils;

import java.util.*;
import java.util.stream.Stream;

@Data
@Getter
@Setter
public class BorrowManager {

    private static Map<String, Borrow> borrows = new HashMap<>();

    private enum borrowOptionsEnum {
        CREATE_BORROW("Create borrow") {
            @Override
            void action(Scanner reader) {
                createBorrow(reader);
            }

            void createBorrow(Scanner reader) {
                System.out.println("[" + borrowOptionsEnum.CREATE_BORROW.getDescription() + "] Coming soon!");
                // Ask for the Student name through the StudentManager?
                String studentName = StudentManager.getStudentFullName(reader);
                // Ask for the Book name through the BookManager?
                String bookName = BookManager.getBookTitle(reader);
                Date startDate = new Date();
                // Create a borrow
                Borrow newBorrow = new Borrow();
                // Store the borrow
            }
        },
        DELETE_BORROW("Delete borrow") {
            @Override
            void action(Scanner reader) {
                deleteBorrow(reader);
            }

            void deleteBorrow(Scanner reader) {
                System.out.println("[" + borrowOptionsEnum.DELETE_BORROW.getDescription() + "] Coming soon!");
                // Option 1.- Ask for the borrow ID
                // Option 2.- Ask for the Student name and the Borrowed book

            }
        },
        CHECK_BORROW("Check borrow") {
            @Override
            void action(Scanner reader) {
                checkBorrow(reader);
            }

            void checkBorrow(Scanner reader) {
                System.out.println("[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Coming soon!");
                // Option 1.- Ask for the borrow ID
            }
        },
        LIST_BORROWS("List borrows") {
            @Override
            void action(Scanner reader) {
                listBorrows();
            }

            void listBorrows() {
                System.out.println("[" + borrowOptionsEnum.LIST_BORROWS.getDescription() + "] Coming soon!");
                // Print all the borrows in a defined format

            }
        },
        UPDATE_BORROW("Update borrow") {
            @Override
            void action(Scanner reader) {
                updateBorrow(reader);
            }

            void updateBorrow(Scanner reader) {
                System.out.println("[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Coming soon!");
                // Ask for the borrow id
                // Ask what field to change
                // - Allowed fields to update: status, due date, return date, etc...
            }
        };

        abstract void action(Scanner reader);
        private final String description;
        borrowOptionsEnum(String action) {
            this.description = action;
        }

        private String getDescription() {
            return this.description;
        }

        private static void printOptions() {
            // Print all the available Borrow options
            System.out.println("[Manage borrows] Available options:");
            borrowOptionsEnum.stream()
                    .map(borrowOptionsEnum::getDescription)
                    .forEach(System.out::println);
        }

        // Use private methods to manipulate borrows as they have sensitive data
        private static borrowOptionsEnum getOption(String action) {
            for (borrowOptionsEnum option : borrowOptionsEnum.values()) {
                if (option.getDescription().equals(action)) {
                    return option;
                }
            }
            return null;
        }

        private static void executeOption(Scanner reader, String action) {
            // compare the action with the available enum and see if it is a valid option
            borrowOptionsEnum option = getOption(action);
            // execute the desired option
            if (option != null) {
                option.action(reader);
            }
            else {
                System.out.println("[Manage borrows] Unknown option, try again");
            }
        }

        public static Stream<borrowOptionsEnum> stream() {
            return Stream.of(borrowOptionsEnum.values());
        }
    }

    public static void start(Scanner reader) {
        // Print the available options
        borrowOptionsEnum.printOptions();
        String action = InterfaceUtils.askString(reader, "[Manage borrows] Select option: ");
        while (!action.equals("Quit")) {
            borrowOptionsEnum.executeOption(reader, action);
            action = InterfaceUtils.askString(reader, "[Manage borrows] Select option: ");
        }
    }
}
