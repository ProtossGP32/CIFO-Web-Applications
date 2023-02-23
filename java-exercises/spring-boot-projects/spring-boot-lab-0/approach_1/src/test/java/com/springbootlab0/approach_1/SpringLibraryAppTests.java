package com.springbootlab0.approach_1;

import com.github.javafaker.Faker;
import com.springbootlab0.approach_1.domain.Book;
import com.springbootlab0.approach_1.domain.CD;
import com.springbootlab0.approach_1.domain.DVD;
import com.springbootlab0.approach_1.repository.*;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;

@SpringBootTest
class SpringLibraryAppTests {

	private static Faker faker;

	// Define dependency injections
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	CDRepository cdRepository;
	@Autowired
	DVDRepository dvdRepository;
	//@Autowired
	//UserRepository userRepository;
	//@Autowired
	//LibrarianRepository librarianRepository;

	@BeforeAll
	static void initialize() {
		faker = new Faker();
	}

	@Test
	void createObjects() {
		// Create a Book object
		Book book1 = FakeDataGenerator.createFakeBook();
		// Save the Book object into the repository
		bookRepository.save(book1);

		// Same with a CD
		CD cd1 = FakeDataGenerator.createFakeCD();
		cdRepository.save(cd1);

		// Same with a DVD
		DVD dvd1 = FakeDataGenerator.createFakeDVD();
		dvdRepository.save(dvd1);
	}


	@Test
	void contextLoads() {
	}

}
