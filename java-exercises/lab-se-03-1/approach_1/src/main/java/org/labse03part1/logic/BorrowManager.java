package org.labse03part1.logic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.labse03part1.domain.Author;
import org.labse03part1.domain.Book;
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
        CREATE_BORROW("Create Borrow"),
        DELETE_BORROW("Delete Borrow"),
        GET_BORROW("Get Borrow"),
        LIST_BORROWS("List Borrows"),
        UPDATE_BORROW("Update Borrow");

        private final String action;
        borrowOptionsEnum(String action) {
            this.action = action;
        }

        private String getAction() {
            return this.action;
        }

        public static Stream<borrowOptionsEnum> stream() {
            return Stream.of(borrowOptionsEnum.values());
        }
    }

    public static void start(Scanner reader) {
        // Print the available options
        printOptions();
        String option = InterfaceUtils.askString(reader, "- Select option ('Quit' to exit):");
        executeOption(reader, option);
    }

    private static void printOptions() {
        // Print all the available Borrow options
        System.out.println("Available options:");
        borrowOptionsEnum.stream()
                .map(borrowOptionsEnum::getAction)
                .forEach(System.out::println);
    }



    private static boolean executeOption(Scanner reader, String action) {
        // compare the action with the available enum and see if it is a valid option
        borrowOptionsEnum option = getOption(action);
        // execute the desired option
        if (option != null) {
            switch (option) {
                case CREATE_BORROW -> {
                    return addBorrow(reader);
                }
                case DELETE_BORROW -> {
                    return deleteBorrow(reader);
                }
                case GET_BORROW -> {
                    return getBorrow(reader);
                }
                case LIST_BORROWS -> {
                    return listBorrows(reader);
                }
                case UPDATE_BORROW -> {
                    return updateBorrow(reader);
                }
                default -> throw new IllegalStateException("Unexpected value: " + option);
            }
        }
        else {
            System.out.println("Unknown option, try again");
        }
        return false;
    }

    // Use private methods to manipulate books as they are sensitive data

    private static borrowOptionsEnum getOption(String action) {
        for (borrowOptionsEnum option : borrowOptionsEnum.values()) {
            if (option.getAction().equals(action)) {
                return option;
            }
        }
        return null;
    }

    private static boolean addBorrow(Scanner reader) {
        return false;
    }

    private static boolean deleteBorrow(Scanner reader) {
        return false;
    }

    private static boolean getBorrow(Scanner reader) {
        return false;
    }

    private static boolean listBorrows(Scanner reader) {
        return false;
    }

    private static boolean updateBorrow(Scanner reader) {
        return false;
    }
}
