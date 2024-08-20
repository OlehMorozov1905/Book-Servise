package ait.cohort34.book.dao;

import ait.cohort34.book.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepository {

    Optional<Author> findById(String name);

    Author save(Author author);

    void deleteById(String authorName);
}


