package org.labse03part1.logic;

import com.github.javafaker.Faker;
import org.labse03part1.domain.Author;
import org.labse03part1.utils.InterfaceUtils;

import java.util.*;
import java.util.stream.Stream;

public class AuthorManager {
    private static Map<String, Author> authors = new HashMap<>();

    private enum authorOptionsEnum {
        ADD_AUTHOR("Add author"),
        DELETE_AUTHOR("Delete author"),
        GET_AUTHOR("Get author"),
        LIST_AUTHORS("List authors"),
        UPDATE_AUTHOR("Update author");

        private final String action;
        authorOptionsEnum(String action) {
            this.action = action;
        }

        private String getAction() {
            return this.action;
        }

        public static Stream<authorOptionsEnum> stream() {
            return Stream.of(authorOptionsEnum.values());
        }
    }

    public static void initializeRandomActors() {
        if (authors.isEmpty()) {
            Random randomNum = new Random();
            createFakeAuthors(randomNum.nextInt(0, 10));
        }
    }

    public static void start(Scanner reader) {
        printOptions();
        String action = InterfaceUtils.askString(reader, "- Select option:");
        Object result = executeOption(reader, action);
    }

    private static authorOptionsEnum getOption(String action) {
        for (authorOptionsEnum option : authorOptionsEnum.values()) {
            if (option.getAction().equals(action)) {
                return option;
            }
        }
        return null;
    }

    private static Object executeOption(Scanner reader, String action) {
        authorOptionsEnum option = getOption(action);
        if (option != null) {
            switch (option) {
                case ADD_AUTHOR -> {
                    return addAuthor(reader);
                }
                case DELETE_AUTHOR -> {
                    return deleteAuthor(reader);
                }
                case GET_AUTHOR -> {
                    return getAuthor(reader);
                }
                case LIST_AUTHORS -> {
                }
                case UPDATE_AUTHOR -> {
                }
            }
        }
        return null;
    }

    private static boolean deleteAuthor(Scanner reader) {
        return false;
    }
    public static Author getAuthor(Scanner reader) {
        listAuthors();
        String authorFullName = InterfaceUtils.askString(reader, "Enter author full name");
        while(getAuthor(authorFullName) == null) {
            System.out.println("Invalid author name!");
            authorFullName = InterfaceUtils.askString(reader, "Enter author full name");
        }

        return getAuthor(authorFullName);
    }

    private static Author getAuthor(String authorFullName) {
        // Return null by default if the author doesn't exist in our storage
        return authors.getOrDefault(authorFullName, null);
    }

    public static Author getRandomAuthor() {
        Random randomNum = new Random();
        List<String> authorNames = authors.keySet().stream().toList();
        String randomAuthor = authorNames.get(randomNum.nextInt(0, authorNames.size()));
        return authors.get(randomAuthor);
    }

    public static void listAuthors() {
        System.out.println(authors.keySet());
    }

    private static void printOptions() {
        // Print all the available Borrow options
        System.out.println("Available options:");
        authorOptionsEnum.stream()
                .map(authorOptionsEnum::getAction)
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


    private static boolean addAuthor(Scanner reader) {
        // Ask for the author's information
        // Create an author
        // TODO: Check if we already have that author in our system
        while (true) {
            String authorFirstName = InterfaceUtils.askString(reader, "Enter author's first name ('Quit' to exit):");
            if (authorFirstName.equals("Quit")) {
                return false;
            }
            String authorLastName = InterfaceUtils.askString(reader, "Enter author's last name:");
            String authorFullName = authorFirstName + " " + authorLastName;
            // Check if the author already exists in the system
            if (!authors.containsKey(authorFullName)) {
                int authorAge = InterfaceUtils.askInt(reader, "Enter author's age");
                String authorGenre = InterfaceUtils.askString(reader, "Enter author's main genre");
                authors.put(authorFirstName + " " + authorLastName, new Author(authorFirstName, authorLastName, authorAge, authorGenre));
                return true;
            }
            System.out.println("Author" + authorFullName + " already exists! Enter a new one");
        }
    }
}
