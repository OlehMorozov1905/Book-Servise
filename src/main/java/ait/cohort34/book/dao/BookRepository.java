package ait.cohort34.book.dao;

import ait.cohort34.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, String> {

    Stream<Book> findByAuthorsName(String authorName);

    Stream<Book> findByPublisher_PublisherName(String publisherName);
}
