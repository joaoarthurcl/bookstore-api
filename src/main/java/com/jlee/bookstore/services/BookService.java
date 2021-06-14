package com.jlee.bookstore.services;

import com.jlee.bookstore.domain.Book;
import com.jlee.bookstore.dto.BookDTO;
import com.jlee.bookstore.exceptions.ObjectNotFoundException;
import com.jlee.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Integer id) {
        Optional<Book> bookId = bookRepository.findById(id);
        return bookId.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found. Id = " + id + " and Type: " + Book.class.getName()));
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book create(Book book) {
        book.setId(null);
        return this.bookRepository.save(book);
    }

    @Transactional
    public Book update(Integer id, BookDTO bookDTO) {
        final var obj = findById(id);
        if (bookDTO.getTitle() != null && !bookDTO.getTitle().isEmpty()) {
            obj.setTitle(bookDTO.getTitle());
        }
        if (bookDTO.getAuthor_name() != null && !bookDTO.getAuthor_name().isEmpty()) {
            obj.setAuthor_name(bookDTO.getAuthor_name());
        }
        if (bookDTO.getDescription() != null && !bookDTO.getDescription().isEmpty()) {
            obj.setDescription(bookDTO.getDescription());
        }
        return this.bookRepository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        this.bookRepository.deleteById(id);
    }
}
