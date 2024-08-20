package ait.cohort34.book.dao;

import ait.cohort34.book.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<String> findByPublishersAuthor(String authorName) {
        return List.of();
    }

    @Override
    public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
        return Stream.empty();
    }

    @Override
    public Optional<Publisher> findById(String publisher) {
        return Optional.ofNullable(entityManager.find(Publisher.class, publisher));
    }

    @Override
    public Publisher save(Publisher publisher) {
        entityManager.persist(publisher);
        return publisher;
    }
}
