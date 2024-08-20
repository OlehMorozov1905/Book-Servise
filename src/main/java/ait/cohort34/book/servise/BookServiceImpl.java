package ait.cohort34.book.servise;

import ait.cohort34.book.dao.AuthorRepository;
import ait.cohort34.book.dao.BookRepository;
import ait.cohort34.book.dao.PublisherRepository;
import ait.cohort34.book.dto.AuthorDto;
import ait.cohort34.book.dto.BookDto;
import ait.cohort34.book.dto.exeptions.NotFoundException;
import ait.cohort34.book.model.Author;
import ait.cohort34.book.model.Book;
import ait.cohort34.book.model.Publisher;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisher;
    final ModelMapper modelMapper;
    private final PublisherRepository publisherRepository;

    @Transactional
    @Override
    public Boolean addBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        //Publisher handle
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
                .orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
        //Authors handle;
        Set<Author> authors = bookDto.getAuthors()
                .stream()
                .map(a -> authorRepository.findById(a.getName())
                        .orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
                .collect(Collectors.toSet());
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
        bookRepository.save(book);
        return true;
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto removeBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.delete(book);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto updateBookTittle(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        return modelMapper.map(book, BookDto.class);
    }

//    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByAuthor(String authorName) {
//        return bookRepository.findByAuthorsName(authorName)
//                .map(b -> modelMapper.map(b, BookDto.class))
//                .toList();
        Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
        return author.getBooks()
                .stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

//    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByPublisher(String publisherName) {
//        return bookRepository.findByPublisher_PublisherName(publisherName)
//                .map(b -> modelMapper.map(b, BookDto.class))
//                .toList();
        Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(EntityNotFoundException::new);
        return publisher.getBooks()
                .stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

//    @Transactional(readOnly = true)
    @Override
    public Iterable<AuthorDto> findBookAuthors(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return book.getAuthors()
                .stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<String> findPublishersByAuthor(String authorName) {
//        return publisherRepository.findByPublishersAuthor(authorName);
        return publisherRepository.findDistinctByBooksAuthorsName(authorName)
                .map(Publisher::getPublisherName)
                .toList();
    }

    @Override
    @Transactional
    public AuthorDto removeAuthor(String authorName) {
        Author author = authorRepository.findById(authorName).orElseThrow(NotFoundException::new);

        // Удаляем автора
        authorRepository.deleteById(authorName);

        // Возвращаем удаленного автора как DTO
        return modelMapper.map(author, AuthorDto.class);
    }
}
