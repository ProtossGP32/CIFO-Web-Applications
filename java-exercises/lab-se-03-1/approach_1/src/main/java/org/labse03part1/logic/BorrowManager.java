package org.labse03part1.logic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.labse03part1.domain.Borrow;
import org.labse03part1.utils.InterfaceUtils;

import java.util.*;
import java.util.stream.Stream;

import static org.labse03part1.utils.InterfaceUtils.askString;

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
                System.out.println("[" + borrowOptionsEnum.CREATE_BORROW.getDescription() + "] Creating new borrow...");
                // Ask for the Student name through the StudentManager?
                String studentID = StudentManager.getStudentID(reader);
                // Ask for the Book name through the BookManager
                // - Only show the available books
                String bookID = BookManager.getAvailableBookID(reader);
                // Create a borrow
                Borrow newBorrow = new Borrow(studentID, bookID);
                // Store the borrow
                borrows.put(newBorrow.getBorrowID(), newBorrow);
                System.out.println("[" + borrowOptionsEnum.CREATE_BORROW.getDescription() + "] New borrow created:");
                System.out.println("- Borrow ID: " + newBorrow.getBorrowID());
                System.out.println("- Borrow details: " + newBorrow);
                // Set the book availability to false through the BookManager
                BookManager.setBookAvailability(bookID, false);

            }
        },
        DELETE_BORROW("Delete borrow") {
            @Override
            void action(Scanner reader) {
                deleteBorrow(reader);
            }

            void deleteBorrow(Scanner reader) {
                borrowOptionsEnum.LIST_BORROWS.action(reader);
                // Option 1.- Ask for the borrow ID
                String borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.DELETE_BORROW.getDescription() + "] Enter borrow ID ('Quit' to exit): ");
                while (!borrows.containsKey(borrowID)) {
                    if (borrowID.equals("Quit")) {
                        System.out.println("[" + borrowOptionsEnum.DELETE_BORROW.getDescription() + "] Delete borrow cancelled.");
                        return;
                    }
                    borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.DELETE_BORROW.getDescription() + "] Unknown ID. Enter borrow ID ('Quit' to exit): ");
                }

                Borrow deletedBorrow = borrows.remove(borrowID);
                // TODO: Set the book availability to true
                BookManager.setBookAvailability(deletedBorrow.getBookID(), false);
                System.out.println("[" + borrowOptionsEnum.DELETE_BORROW.getDescription() + "] Borrow " + deletedBorrow.getBorrowID() + " deleted!");
            }
        },
        CHECK_BORROW("Check borrow") {
            @Override
            void action(Scanner reader) {
                checkBorrow(reader);
            }

            void checkBorrow(Scanner reader) {
                borrowOptionsEnum.LIST_BORROWS.action(reader);
                // Option 1.- Ask for the borrow ID
                String borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Enter borrow ID ('Quit' to exit): ");
                while (!borrows.containsKey(borrowID)) {
                    if (borrowID.equals("Quit")) {
                        System.out.println("[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Check borrow cancelled.");
                        return;
                    }
                    borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Unknown ID. Enter borrow ID ('Quit' to exit): ");
                }
                // Print the borrow information
                System.out.println(borrows.get(borrowID));

            }
        },
        LIST_BORROWS("List borrows") {
            @Override
            void action(Scanner reader) {
                listBorrows();
            }

            void listBorrows() {
                System.out.println("[" + borrowOptionsEnum.LIST_BORROWS.getDescription() + "] Available 'In progress' borrows:");
                // Print all the borrows in a defined format
                borrows.entrySet().stream()
                        .filter(entry -> entry.getValue().getStatusDescription().equals("In progress"))
                        .map(x -> x.getKey() + ": " + x.getValue())
                        .forEach(x -> System.out.println(x));
            }
        },
        UPDATE_BORROW("Update borrow") {
            @Override
            void action(Scanner reader) {
                updateBorrow(reader);
            }

            void updateBorrow(Scanner reader) {

                // Ask for the borrow id
                borrowOptionsEnum.LIST_BORROWS.action(reader);
                // Option 1. Ask for the borrow ID
                /*
                String borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Enter borrow ID ('Quit' to exit): ");
                while (!borrows.containsKey(borrowID)) {
                    if (borrowID.equals("Quit")) {
                        System.out.println("[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Check borrow cancelled.");
                        return;
                    }
                    borrowID = InterfaceUtils.askString(reader, "[" + borrowOptionsEnum.CHECK_BORROW.getDescription() + "] Unknown ID. Enter borrow ID ('Quit' to exit): ");
                }
                Borrow borrowToUpdate = borrows.get(borrowID);
                */
                // Option 2. Invoke a borrow finding method
                Borrow borrowToUpdate = findBorrow(reader);
                if (borrowToUpdate == null) {
                    System.out.println("Error finding the borrow. Exiting...");
                }

                System.out.println(borrowToUpdate);
                // Ask what field to change
                // - Allowed fields to update: status, due date, return date, etc...
                System.out.println("[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Coming soon!");
                String parameter = askString(reader, "[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Enter the parameter to modify ('Quit' to exit): ");
                Object value;
                while (!parameter.equals("Quit")) {
                    switch(parameter) {
                        case "status" -> {
                            value = askString(reader, "[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Change the status to 'In Progress', 'Late' or 'Closed': ");
                            borrowToUpdate.setStatus(String.valueOf(value));
                            // TODO: If status is set to "Closed", then book availability must be set to true
                            if (borrowToUpdate.getStatusDescription().equals("Closed")) {
                                BookManager.setBookAvailability(borrowToUpdate.getBookID(), true);
                            }
                        }
                        case "dueBorrowDate" -> {
                            // The due borrow date is updated to a later date
                            value = askString(reader, "[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Enter the new due date in a YYYY/mm/dd format: ");
                            borrowToUpdate.setDueBorrowDate(String.valueOf(value));
                            //
                        }
                        default -> System.out.println("[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] " + parameter + "is a read-only parameter, choose another one");
                    }
                    parameter = askString(reader, "[" + borrowOptionsEnum.UPDATE_BORROW.getDescription() + "] Insert the parameter to modify ('Quit' to exit): ");
                }
            }
        },
        RETURN_BOOK("Return book") {
            @Override
            void action(Scanner reader) {
                returnBook(reader);
            }

            private void returnBook(Scanner reader) {
                // Find the borrow of the book
                Borrow borrowToUpdate = findBorrow(reader);
                if (borrowToUpdate == null) {
                    System.out.println("Error finding the borrow. Exiting...");
                    return;
                }
                // Set the borrow status to "Close"
                borrowToUpdate.setStatus("Closed");
                // Set the book availability to True
                BookManager.setBookAvailability(borrowToUpdate.getBookID(), true);
                System.out.println("[" + borrowOptionsEnum.RETURN_BOOK.getDescription() + "] Book " + BookManager.getBookTitle(borrowToUpdate.getBookID()));
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
        String action = InterfaceUtils.askString(reader, "[Manage borrows] Select option ('Quit' to exit): ");
        while (!action.equals("Quit")) {
            borrowOptionsEnum.executeOption(reader, action);
            System.out.println();
            borrowOptionsEnum.printOptions();
            action = InterfaceUtils.askString(reader, "[Manage borrows] Select option ('Quit' to exit): ");
        }
    }

    public static Borrow findBorrow(Scanner reader) {
        String knownObject = askString(reader, "[Borrow Manager] Enter known object name ('Quit' to exit): ");
        while (!knownObject.equals("book") && !knownObject.equals("user") && !knownObject.equals("borrow")) {
            if (knownObject.equals("Quit")) {
                System.out.println("Find Borrow cancelled");
                return null;
            }
            knownObject = askString(reader, "[Borrow Manager] Unknown object. Enter known object name ('Quit' to exit): ");
        }

        String objectID = askString(reader, "[Book Manager] Enter " + knownObject + " ID: ");
        switch(knownObject) {
            case "book" -> {
                return findBorrowByBookID(objectID);
            }
            case "user" -> {
                return findBorrowByUserID(reader, objectID);
            }
            case "borrow" ->  {
                // Just return the borrow if it exists
                return borrows.getOrDefault(objectID, null);
            }
            default -> {
                return null;
            }
        }
    }

    public static Borrow findBorrowByBookID(String bookID) {
        for (Map.Entry<String, Borrow> entry : borrows.entrySet()) {
            Borrow borrow = entry.getValue();
            if (borrow.getBookID().equals(bookID)) {
                return borrow;
            }
        }
        return null;
    }

    public static Borrow findBorrowByUserID(Scanner reader, String userID) {
        // 1. Find all borrows of that user
        List<Borrow> userBorrows = new ArrayList<>();
        for (Map.Entry<String, Borrow> entry : borrows.entrySet()) {
            Borrow borrow = entry.getValue();
            if (borrow.getStudentID().equals(userID)) {
                userBorrows.add(borrow);
            }
        }

        // 2.- Text-only: show all available borrows
        for (Borrow borrow : userBorrows) {
            if (borrow.getStatusDescription().equals("In progress") || borrow.getStatusDescription().equals("Late")) {
                System.out.println("Borrow ID: " + borrow.getBorrowID() + ", book: " + BookManager.getBookTitle(borrow.getBookID()) + ", status: " + borrow.getStatusDescription());
            }
        }

        // 3. Let the user decide what borrow to get
        String selectedBorrow = askString(reader, "[Borrow Manager] Enter the user's borrow ID: ");
        while (!borrows.containsKey(selectedBorrow)) {
            if (selectedBorrow.equals("Quit")) {
                System.out.println("[Borrow Manager] Find borrow cancelled.");
                return null;
            }
            selectedBorrow = askString(reader, "[Borrow Manager] Unknown ID. Enter the user's borrow ID: ");
        }

        // 4. Return the borrow
        return borrows.get(selectedBorrow);
    }
}
