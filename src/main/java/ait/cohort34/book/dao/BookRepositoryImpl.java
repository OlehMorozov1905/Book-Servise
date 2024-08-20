package ait.cohort34.book.dao;

import ait.cohort34.book.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Stream<Book> findByAuthorsName(String authorName) {
        return Stream.empty();
    }

    @Override
    public Stream<Book> findByPublisher_PublisherName(String publisherName) {
        return Stream.empty();
    }

    @Override
    public boolean existsById(String isbn) {
        return entityManager.find(Book.class, isbn) != null;
    }

    @Override
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(String isbn) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Book book) {

    }
}
