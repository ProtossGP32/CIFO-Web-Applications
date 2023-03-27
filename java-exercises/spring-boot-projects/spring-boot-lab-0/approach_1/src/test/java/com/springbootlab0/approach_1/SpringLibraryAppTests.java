package com.springbootlab0.approach_1;

import com.github.javafaker.Faker;
import com.springbootlab0.approach_1.domain.Book;
import com.springbootlab0.approach_1.domain.CD;
import com.springbootlab0.approach_1.domain.DVD;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.repository.*;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class SpringLibraryAppTests {

	// Define dependency injections
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PublicationRepository publicationRepository;

	@Test
	void createObjects() {
		Optional<Publication> publicationFromDB;
		// Create a Book object
		Book book1 = FakeDataGenerator.createFakeBook();
		// Save the author into the database
		authorRepository.save(book1.getAuthor());
		// Save the Book object into the repository
		publicationRepository.save(book1);

		// Retrieve the book from the DB
		publicationFromDB = publicationRepository.findById(book1.getId());
		if (publicationFromDB.isPresent()) {
			assertEquals(book1, publicationFromDB.get());
		} else {
			fail("Book not found in DB");
		}


		// Same with a CD
		CD cd1 = FakeDataGenerator.createFakeCD();
		// Save the author into the database
		authorRepository.save(cd1.getAuthor());
		publicationRepository.save(cd1);

		// Retrieve the CD from the DB
		publicationFromDB = publicationRepository.findById(cd1.getId());
		if (publicationFromDB.isPresent()) {
			assertEquals(cd1, publicationFromDB.get());
		} else {
			fail("CD not found in DB");
		}


		// Same with a DVD
		DVD dvd1 = FakeDataGenerator.createFakeDVD();
		// Save the author into the database
		authorRepository.save(dvd1.getAuthor());
		publicationRepository.save(dvd1);

		// Retrieve the DVD from the DB
		publicationFromDB = publicationRepository.findById(dvd1.getId());
		if (publicationFromDB.isPresent()) {
			assertEquals(dvd1, publicationFromDB.get());
		} else {
			fail("DVD not found in DB");
		}
	}


	@Test
	void contextLoads() {
	}

}
