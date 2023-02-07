package org.labse03part1.logic;

import org.labse03part1.domain.Book;
import org.labse03part1.domain.Car;
import org.labse03part1.domain.Student;

import java.util.*;
import java.util.stream.Stream;

import static org.labse03part1.utils.InterfaceUtils.askInt;
import static org.labse03part1.utils.InterfaceUtils.askString;

public class StudentManager {
    private static final Map<String, Student> students = new HashMap<>();

    private enum studentOptionsEnum {
        ADD_STUDENT("Add student") {
            @Override
            void action(Scanner reader) {
                addStudent(reader);
            }

            private void addStudent(Scanner reader) {
                while (true) {
                    // Ask for the student's details
                    String studentFirstName = askString(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's first name ('Quit' to exit): ");
                    if (studentFirstName.equals("Quit")) {
                        break;
                    }
                    // Ask for the rest of the student's information
                    String studentLastName = askString(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's last name ('Quit' to exit): ");
                    String studentFullName = studentFirstName + " " + studentLastName;
                    if (!students.containsKey(studentFullName)) {
                        int studentAge = askInt(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's age ('Quit' to exit): ");
                        String studentUniversity = askString(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's university ('Quit' to exit): ");
                        List<Book> studentBooks = new ArrayList<>();
                        // TODO: Select an available car or create a new one from start
                        Car studentCar = new Car();
                        // Insert the student into the storage - Key: studentFullName
                        students.put(studentFullName, new Student(studentFirstName, studentLastName, studentAge, studentUniversity, studentBooks, studentCar));
                        System.out.println("[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Student " + studentFullName + " added!");
                        break;
                    }
                    System.out.println("[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Student " + studentFullName + " already exists! Enter a new one");
                }
            }
        },
        DELETE_STUDENT("Delete student") {
            @Override
            void action(Scanner reader) {
                deleteStudent(reader);
            }

            private void deleteStudent(Scanner reader) {
                studentOptionsEnum.LIST_STUDENTS.action(reader);
                String studentFullName = getStudentFullName(reader);

                students.remove(studentFullName);
                System.out.println("[" + studentOptionsEnum.DELETE_STUDENT.getDescription() + "] Student " + studentFullName + " deleted!");
            }

        },
        CHECK_STUDENT("Check student") {
            @Override
            void action(Scanner reader) {
                checkStudent(reader);
            }

            private void checkStudent(Scanner reader) {
                studentOptionsEnum.LIST_STUDENTS.action(reader);
                String studentFullName = getStudentFullName(reader);

                System.out.println(students.get(studentFullName));
            }
        },
        LIST_STUDENTS("List students") {
            @Override
            void action(Scanner reader) {
                listStudents(reader);
            }

            private void listStudents(Scanner reader) {
                System.out.println("[" + studentOptionsEnum.CHECK_STUDENT.getDescription() + "] Available students:");
                students.keySet()
                        .forEach(System.out::println);
            }
        },
        UPDATE_STUDENT("Update student") {
            @Override
            void action(Scanner reader) {
                updateStudent(reader);
            }

            private void updateStudent(Scanner reader) {
                // Ask for the student name
                studentOptionsEnum.LIST_STUDENTS.action(reader);
                String studentFullName = getStudentFullName(reader);

                // Retrieve the student to update
                Student studentToUpdate = students.get(studentFullName);
                System.out.println(studentToUpdate);

                // Once the student is found, ask for the parameter to change
                String parameter = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the parameter to modify ('Quit' to exit): ");

                // Define an Object variable to store the different types of answers
                Object value;

                while (!parameter.equals("Quit")) {
                    switch (parameter) {
                        case "firstName" -> {
                            value = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new first name: ");
                            studentToUpdate.setFirstName(value.toString());
                            System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] First name set to " + value);
                        }
                        case "lastName" -> {
                            value = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new last name: ");
                            studentToUpdate.setLastName(value.toString());
                            System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Last name set to " + value);
                        }
                        case "age" -> {
                            value = askInt(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new age: ");
                            studentToUpdate.setAge((int) value);
                            System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Age set to " + value);
                        }
                        case "university" -> {
                            value = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new university: ");
                            studentToUpdate.setUniversity(value.toString());
                            System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] University set to " + value);
                        }
                        case "books" -> {
                            // TODO: Call a book manager for the student's list of books. Maybe a public static method in BookManager that accepts an ArrayList of Books?
                            System.out.println("Books Management coming soon!");
                            //value = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new car: ");
                            // Some BookManager logic
                            //System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Car set to " + value);
                        }
                        case "car" -> {
                            // TODO: Access the CarManager to retrieve a new car, similar to what's being done with the books and its Author
                            System.out.println("Car Management coming soon!");
                            //value = askString(reader, "[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Enter the student's new car: ");
                            // Some CarManager logic
                            //System.out.println("[" + studentOptionsEnum.UPDATE_STUDENT.getDescription() + "] Car set to " + value);
                        }
                        default -> System.out.println("[Manage students] " + parameter + " is a read-only parameter, choose another one");
                    }
                }
            }
        };
        
        abstract void action(Scanner reader);

        private final String description;

        studentOptionsEnum(String description) {
            this.description = description;
        }

        private String getDescription() {
            return this.description;
        }

        private static void printOptions() {
            // Print all the available Student options
            System.out.println("[Manage students] Available options:");
            studentOptionsEnum.stream()
                    .map(studentOptionsEnum::getDescription)
                    .forEach(System.out::println);
        }

        private static studentOptionsEnum getOption(String action) {
            for (studentOptionsEnum option : studentOptionsEnum.values()) {
                if (option.getDescription().equals(action)) {
                    return option;
                }
            }
            return null;
        }

        private static void executeOption(Scanner reader, String description) {
            studentOptionsEnum option = getOption(description);
            if (option != null) {
                option.action(reader);
            }
            else {
                System.out.println("[Manage students] Invalid option! Try again.");
            }
        }

        private static Stream<studentOptionsEnum> stream() {
            return Stream.of(studentOptionsEnum.values());
        }
    }

    // Entrypoint of Student Manager
    public static void start(Scanner reader) {
        // Print available options
        studentOptionsEnum.printOptions();
        String description = askString(reader, "[Manage students] Select option ('Quit' to exit): ");
        while (!description.equals("Quit")) {
            studentOptionsEnum.executeOption(reader, description);
            System.out.println();
            studentOptionsEnum.printOptions();
            description = askString(reader, "[Manage students] Select option ('Quit' to exit): ");
        }
    }

    // StudentManager utilities
    private static String getStudentFullName(Scanner reader) {
        studentOptionsEnum.LIST_STUDENTS.action(reader);
        String studentFullName = askString(reader, "- Enter student's full name: ");
        while (!students.containsKey(studentFullName)) {
            System.out.println("- Invalid student name!");
            studentFullName = askString(reader, "- Enter student's full name: ");
        }
        return studentFullName;
    }

}
