package ait.cohort34.book.dao;

import ait.cohort34.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository {

    Stream<Book> findByAuthorsName(String authorName);

    Stream<Book> findByPublisher_PublisherName(String publisherName);

    boolean existsById(String isbn);

    Book save(Book book);

    Optional<Book> findById(String isbn);

    void deleteById(Book book);
}
