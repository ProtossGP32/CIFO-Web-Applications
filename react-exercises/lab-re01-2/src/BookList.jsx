import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BookList = () => {
    const [books, setBooks] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    // useEffect hook ensures that the BookList component stays connected to the SpringBoot API while displayed on the page
    useEffect(() => {
        // Define the fetchBooks function (?)
        const fetchBooks = async () => {
            const response = await axios.get(
                'http://localhost:9090/api/publications/books'
            );
            
            const booksData = response.data;
            setBooks(booksData);
            setIsLoading(false);

        };
        // Invoke the fetchBooks function
        fetchBooks();
        // TODO: return a clean code function with cleanup code that disconnects from the SpringBoot API

    }, []); // No dependencies required nor values from the component used inside of those functions

    return (
        <>
            <h1>Books</h1>
            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <ul>
                    {books.map((book) => (
                        <li key={book.id}>
                            <strong>Title:</strong> {book.title} <br />
                            <strong>Year:</strong> {book.publicationDate} <br />
                            <strong>Author:</strong> {book.author.firstName + " " + book.author.lastName} <br />
                            <strong>ISBN:</strong> {book.isbn} <br />
                            <strong>Pages:</strong> {book.pages} <br />
                            <strong>Genre:</strong> {book.genre} <br />
                        </li>
                    ))}
                </ul>
            )}
        </>
    );
};

export default BookList;