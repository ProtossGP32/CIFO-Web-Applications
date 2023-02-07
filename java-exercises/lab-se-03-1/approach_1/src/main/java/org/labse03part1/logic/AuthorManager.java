package org.labse03part1.logic;

import com.github.javafaker.Faker;
import org.labse03part1.domain.Author;
import org.labse03part1.utils.InterfaceUtils;

import java.util.*;
import java.util.stream.Stream;

import static org.labse03part1.utils.InterfaceUtils.*;
import static org.labse03part1.utils.InterfaceUtils.askString;

public class AuthorManager {
    private static final Map<String, Author> authors = new HashMap<>();

    private enum authorOptionsEnum {
        ADD_AUTHOR("Add author") {
            @Override
            void action(Scanner reader) {
                addAuthor(reader);
            }
        },
        DELETE_AUTHOR("Delete author") {
            @Override
            void action(Scanner reader) {
                deleteAuthor(reader);
            }
        },
        CHECK_AUTHOR("Check author") {
            @Override
            void action(Scanner reader) {
                checkAuthor(reader);
            }
        },
        LIST_AUTHORS("List authors") {
            @Override
            void action(Scanner reader) {
                listAuthors();
            }
        },
        UPDATE_AUTHOR("Update author") {
            @Override
            void action(Scanner reader) {
                updateAuthor(reader);
            }
        };

        abstract void action(Scanner reader);
        private final String description;
        authorOptionsEnum(String description) {
            this.description = description;
        }

        private String getDescription() {
            return this.description;
        }

        public static Stream<authorOptionsEnum> stream() {
            return Stream.of(authorOptionsEnum.values());
        }
    }

    // Entrypoint of the manager
    public static void start(Scanner reader) {
        printOptions();
        String action = askString(reader, "[Manage authors] Select option ('Quit' to exit): ");
        while (!action.equals("Quit")) {
            executeOption(reader, action);
            System.out.println();
            printOptions();
            action = InterfaceUtils.askString(reader, "[Manage authors] Select option ('Quit' to exit): ");
        }
    }

    // Required public methods for external managers
    public static Author getAuthor(Scanner reader) {
        listAuthors();
        String authorFullName = askString(reader, "Enter author's full name: ");
        while(!authors.containsKey(authorFullName)) {
            System.out.println("Invalid author name!");
            authorFullName = askString(reader, "Enter author's full name: ");
        }

        return authors.get(authorFullName);
    }

    public static void initializeRandomActors() {
        if (authors.isEmpty()) {
            Random randomNum = new Random();
            createFakeAuthors(randomNum.nextInt(0, 10));
        }
    }

    public static Author getRandomAuthor() {
        Random randomNum = new Random();
        List<String> authorNames = authors.keySet().stream().toList();
        String randomAuthor = authorNames.get(randomNum.nextInt(0, authorNames.size()));
        return authors.get(randomAuthor);
    }

    // Private methods
    private static authorOptionsEnum getOption(String action) {
        for (authorOptionsEnum option : authorOptionsEnum.values()) {
            if (option.getDescription().equals(action)) {
                return option;
            }
        }
        return null;
    }

    private static void executeOption(Scanner reader, String action) {
        authorOptionsEnum option = getOption(action);
        if (option != null) {
            option.action(reader);
        }
        else {
            System.out.println("[Manage authors] Invalid option! Try again.");
        }
    }

    private static void printOptions() {
        // Print all the available Borrow options
        System.out.println("[Manage authors] Available options:");
        authorOptionsEnum.stream()
                .map(authorOptionsEnum::getDescription)
                .forEach(System.out::println);
    }

    private static void createFakeAuthors(int number) {
        Faker faker = new Faker();
        Author newAuthor;
        // Create as many new Authors as requested
        for (int i = 0; i < number; i++) {
            newAuthor = new Author();
            newAuthor.setFirstName(faker.name().firstName());
            newAuthor.setLastName(faker.name().lastName());
            newAuthor.setAge(faker.number().numberBetween(0, 100));
            newAuthor.setGenre(faker.book().genre());
            String authorFullName = newAuthor.getFirstName() + " " + newAuthor.getLastName();
            // Store the new fake author
            authors.put(authorFullName, newAuthor);
        }
    }

    private static void addAuthor(Scanner reader) {
        while (true) {
            // Ask for the author's details
            String authorFirstName = askString(reader, "[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Enter author's first name ('Quit' to exit): ");
            if (authorFirstName.equals("Quit")) {
                break;
            }
            String authorLastName = askString(reader, "[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Enter author's last name: ");
            String authorFullName = authorFirstName + " " + authorLastName;
            // Check if the author already exists in the system
            if (!authors.containsKey(authorFullName)) {
                int authorAge = askInt(reader, "[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Enter author's age: ");
                String authorGenre = askString(reader, "[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Enter author's main genre: ");
                authors.put(authorFirstName + " " + authorLastName, new Author(authorFirstName, authorLastName, authorAge, authorGenre));
                System.out.println("[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Author " + authorFullName + " added!");
                break;
            }
            System.out.println("[" + authorOptionsEnum.ADD_AUTHOR.getDescription() + "] Author" + authorFullName + " already exists! Enter a new one");
        }
    }
    private static void checkAuthor(Scanner reader) {
        listAuthors();
        String authorFullName = askString(reader, "[" + authorOptionsEnum.CHECK_AUTHOR.getDescription() + "] Enter author's full name: ");
        while(!authors.containsKey(authorFullName)) {
            System.out.println("[" + authorOptionsEnum.CHECK_AUTHOR.getDescription() + "] Invalid author name!");
            authorFullName = askString(reader, "[" + authorOptionsEnum.CHECK_AUTHOR.getDescription() + "] Enter author's full name: ");
        }

        System.out.println(authors.get(authorFullName));
    }

    private static void listAuthors() {
        System.out.println("[" + authorOptionsEnum.LIST_AUTHORS.getDescription() + "] Available authors:");
        authors.keySet()
                .forEach(System.out::println);
    }

    private static void updateAuthor(Scanner reader) {
        // Ask for the author name
        String authorFullName = getAuthorFullName(reader);
        while (!authors.containsKey(authorFullName)) {
            if (authorFullName.equals("Quit")) {
                System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Update author cancelled");
                break;
            }
            System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Author " + authorFullName + " doesn't exist in the system. Try again");
            authorFullName = getAuthorFullName(reader);

        }

        // Retrieve the Author object to update
        Author authorToUpdate = authors.get(authorFullName);
        System.out.println(authorToUpdate);

        // Once book is found, ask for the parameter to change
        String parameter = askString(reader, "[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Enter the parameter to modify ('Quit' to exit): ");
        Object value;

        while(!parameter.equals("Quit")) {
            switch (parameter) {
                case "firstName" -> {
                    value = askString(reader, "[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Enter the author's new first name: ");
                    authorToUpdate.setFirstName(value.toString());
                    System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] First name set to " + value);
                }
                case "lastName" -> {
                    value = askString(reader, "[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Enter the author's new last name: ");
                    authorToUpdate.setLastName(value.toString());
                    System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Last name set to " + value);
                }
                case "age" -> {
                    value = askInt(reader, "[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Enter the author's new age: ");
                    authorToUpdate.setAge((int) value);
                    System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Age set to " + value);
                }
                case "genre" -> {
                    value = askString(reader, "[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Enter the author's new main genre: ");
                    authorToUpdate.setGenre(value.toString());
                    System.out.println("[" + authorOptionsEnum.UPDATE_AUTHOR.getDescription() + "] Genre set to " + value);
                }
                default -> System.out.println("[Manage authors] " + parameter + " is a read-only parameter, choose another one");
            }
            // Ask for another parameter to update
            parameter = askString(reader, "[Manage authors] Insert the parameter to modify ('Quit' to exit): ");
        }
    }

    private static String getAuthorFullName(Scanner reader) {
        String authorFirstName = askString(reader, "Enter author's first name ('Quit' to exit): ");
        if (authorFirstName.equals("Quit")) {
            return "Quit";
        }
        String authorLastName = askString(reader, "Enter author's last name: ");
        return authorFirstName + " " + authorLastName;
    }

    private static void deleteAuthor(Scanner reader) {
        String authorFullName = getAuthorFullName(reader);
        while(!authors.containsKey(authorFullName)) {
            if (authorFullName.equals("Quit")) {
                System.out.println("[" + authorOptionsEnum.DELETE_AUTHOR.getDescription() + "] Delete author cancelled");
                break;
            }
            System.out.println("[" + authorOptionsEnum.DELETE_AUTHOR.getDescription() + "] Author " + authorFullName + " doesn't exist in the system!");
            authorFullName = getAuthorFullName(reader);
        }
        // Remove the author from the database
        authors.remove(authorFullName);
        System.out.println("[" + authorOptionsEnum.DELETE_AUTHOR.getDescription() + "] Author " + authorFullName + " deleted!");
    }
}
