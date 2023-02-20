package org.labse03part1.logic;

import com.github.javafaker.Faker;
import org.labse03part1.domain.Book;
import org.labse03part1.domain.Student;

import java.util.*;
import java.util.stream.Stream;

import static org.labse03part1.utils.InterfaceUtils.askInt;
import static org.labse03part1.utils.InterfaceUtils.askString;

public class StudentManager {
    private static final Map<String, Student> students = new HashMap<>();

    public static void initializeRandomStudents() {
        if (students.isEmpty()) {
            Random randomNum = new Random();
            createFakeStudents(randomNum.nextInt(1, 10));
        }
    }

    private static void createFakeStudents(int number) {
        Faker faker = new Faker();
        Student newStudent;
        // Create as many new Students as requested
        for (int i = 0; i < number; i++) {
            newStudent = new Student();
            newStudent.setFirstName(faker.name().firstName());
            newStudent.setLastName(faker.name().lastName());
            newStudent.setAge(faker.number().numberBetween(10, 99));
            newStudent.setUniversity(faker.university().name());
            newStudent.setBooks(new ArrayList<>());

            // Put the fake student into the storage
            students.put(newStudent.getStudentID(), newStudent);
        }
    }

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
                    int studentAge = askInt(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's age ('Quit' to exit): ");
                    String studentUniversity = askString(reader, "[" + studentOptionsEnum.ADD_STUDENT.getDescription() + "] Enter student's university ('Quit' to exit): ");
                    List<Book> studentBooks = new ArrayList<>();
                    // Create the new student object
                    Student newStudent = new Student(studentFirstName, studentLastName, studentAge, studentUniversity, studentBooks);
                    // Check if the student already exists inside the students hashmap
                    if (!students.containsValue(newStudent)) {
                        // Insert the student into the storage - Key: studentID
                        students.put(newStudent.getStudentID(), newStudent);
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
                // Ask for the student ID
                String studentID = askString(reader, "Enter the student ID: ");
                while (!students.containsKey(studentID)) {
                    if (studentID.equals("Quit")) {
                        System.out.println("Delete student cancelled");
                        return;
                    }
                    studentID = askString(reader, "Incorrect ID. Enter the student ID: ");
                }
                Student deletedStudent = students.remove(studentID);
                System.out.println("[" + studentOptionsEnum.DELETE_STUDENT.getDescription() + "] Student " + deletedStudent.getFirstName() + " " + deletedStudent.getLastName() + " deleted!");
            }

        },
        CHECK_STUDENT("Check student") {
            @Override
            void action(Scanner reader) {
                checkStudent(reader);
            }

            private void checkStudent(Scanner reader) {
                studentOptionsEnum.LIST_STUDENTS.action(reader);
                // Ask for the student ID
                String studentID = askString(reader, "Enter the student ID: ");

                System.out.println(students.get(studentID));
            }
        },
        LIST_STUDENTS("List students") {
            @Override
            void action(Scanner reader) {
                listStudents(reader);
            }

            private void listStudents(Scanner reader) {
                System.out.println("[" + studentOptionsEnum.CHECK_STUDENT.getDescription() + "] Available students:");
                students.forEach((studentID, student) -> System.out.println(studentID + ": " + student.getFirstName() + " " + student.getLastName()));
            }
        },
        UPDATE_STUDENT("Update student") {
            @Override
            void action(Scanner reader) {
                updateStudent(reader);
            }

            private void updateStudent(Scanner reader) {
                // TODO: Ask for the student ID instead of the full name
                // Ask for the student name
                studentOptionsEnum.LIST_STUDENTS.action(reader);
                //String studentFullName = getStudentFullName(reader);
                String studentID = askString(reader, "Enter the student ID: ");

                // Retrieve the student to update
                Student studentToUpdate = students.get(studentID);
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
    public static String getStudentID(Scanner reader) {
        studentOptionsEnum.LIST_STUDENTS.action(reader);
        String studentID = askString(reader, "- Enter student ID: ");
        while (!students.containsKey(studentID)) {;
            studentID = askString(reader, "- Invalid student ID! Enter student ID: ");
        }
        return studentID;
    }


}
