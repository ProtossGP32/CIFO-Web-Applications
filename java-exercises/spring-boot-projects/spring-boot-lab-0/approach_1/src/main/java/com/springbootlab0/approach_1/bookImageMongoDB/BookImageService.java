package com.springbootlab0.approach_1.bookImageMongoDB;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class BookImageService {
    @Autowired
    BookImageRepository bookImageRepository;

    public BookImage uploadBookImage(String name, MultipartFile file) throws IOException {
        BookImage bookImage = new BookImage();
        // TODO: Assign alternative ID to avoid exposing sensitive data
        bookImage.setName(name);
        bookImage.setImage(new Binary(file.getBytes()));
        // Save the image into the repository
        return bookImageRepository.save(bookImage);
    }

    public Iterable<BookImage> getAllBookImages() {
        return bookImageRepository.findAll();
    }

    public BookImage getBookImageById(String id) {
        Optional<BookImage> expectedBook = bookImageRepository.findById(id);
        if (expectedBook.isPresent()) {
            return expectedBook.get();
        }
        return null;
    }

    // TODO: updateBookImage

    public BookImage deleteBookImageById(String id) {
        Optional<BookImage> bookImageOptional = bookImageRepository.findById(id);
        if (bookImageOptional.isPresent()) {
            bookImageRepository.deleteById(id);
            return bookImageOptional.get();
        }
        return null;
    }

}
