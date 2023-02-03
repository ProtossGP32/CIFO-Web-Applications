package org.labse03part1.logic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.labse03part1.domain.Book;

import java.util.stream.Stream;

@Data
@Getter
@Setter
public class BorrowManager {

    public enum borrowOptionsEnum {
        ADD_BOOK("Add Book"),
        DELETE_BOOK("Delete Book"),
        GET_ONE_BOOK("Get Book"),
        GET_ALL_BOOKS("List Book"),
        UPDATE_BOOK("Update Book");

        private String action;
        borrowOptionsEnum(String action) {
            this.action = action;
        }

        private String getAction() {
            return this.action;
        }

        public static Stream<borrowOptionsEnum> stream() {
            return Stream.of(borrowOptionsEnum.values());
        }
    };

    public static void printOptions() {
        // Print all the available Borrow options
        borrowOptionsEnum.stream()
                .map(option -> option.getAction())
                .forEach(System.out::println);
    }

    public static borrowOptionsEnum getOption(String action) {
        for (borrowOptionsEnum option : borrowOptionsEnum.values()) {
            if (option.getAction().equals(action)) {
                return option;
            }
        }
        return null;
    }

    public static boolean addBook() {
        return false;
    }

    public static boolean deleteBook() {
        return false;
    }

    public static Book getBook() {
        return new Book();
    }

    public static void ListBooks() {

    }

    public static void updateBook() {

    }
}
