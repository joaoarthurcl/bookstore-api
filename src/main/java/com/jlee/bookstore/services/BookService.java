package com.jlee.bookstore.services;

import com.jlee.bookstore.domain.Book;
import com.jlee.bookstore.exceptions.ObjectNotFoundException;
import com.jlee.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    public Book findById(Integer id) {
        Optional<Book> bookId = bookRepository.findById(id);
        return bookId.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found. Id: " + id + " and Type: " + Book.class.getName()));
    }

    public List<Book> findAll(Integer id_category) {
        this.categoryService.findById(id_category);
        return this.bookRepository.findAllByCategoryIdLike(id_category);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book create(Integer id_category, Book book) {
        book.setId(null);
        final var category = categoryService.findById(id_category);
        book.setCategory(category);
        return this.bookRepository.save(book);
    }

    @Transactional
    public Book update(Integer id, Book book) {
        final var newBook = findById(id);
        updateData(book, newBook);
        return this.bookRepository.save(newBook);
    }

    private void updateData(Book book, Book newBook) {
        if (book.getTitle() != null && !book.getTitle().isEmpty()) {
            newBook.setTitle(book.getTitle());
        }
        if (book.getAuthor_name() != null && !book.getAuthor_name().isEmpty()) {
            newBook.setAuthor_name(book.getAuthor_name());
        }
        if (book.getDescription() != null && !book.getDescription().isEmpty()) {
            newBook.setDescription(book.getDescription());
        }
    }

    public void delete(Integer id) {
        findById(id);
        this.bookRepository.deleteById(id);
    }

}
