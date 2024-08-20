package ait.cohort34.book.servise;

import ait.cohort34.book.dto.AuthorDto;
import ait.cohort34.book.dto.BookDto;


public interface BookService {

    Boolean addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    BookDto removeBookByIsbn(String isbn);

    BookDto updateBookTittle(String isbn, String title);

    Iterable<BookDto> findBooksByAuthor(String authorName);

    Iterable<BookDto> findBooksByPublisher(String publisherName);

    Iterable<AuthorDto> findBookAuthors(String isbn);

    Iterable<String> findPublishersByAuthor(String authorName);

    AuthorDto removeAuthor(String authorName);

}
