package com.jlee.bookstore.controllers;

import com.jlee.bookstore.domain.Book;
import com.jlee.bookstore.dto.BookDTO;
import com.jlee.bookstore.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok().body(BookDTO.ofEntity(book));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue = "0") Integer id_category) {
        final var books = this.bookService.findAll(id_category);
        final var booksDTO = books.stream().map(BookDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(booksDTO);
    }

    @GetMapping("/without-category")
    public ResponseEntity<List<Book>> findAll() {
        final var books = this.bookService.findAll();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestParam(value = "category", defaultValue = "0") Integer id_category, @RequestBody Book book) {
        book = this.bookService.create(id_category, book);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {
        final var obj = this.bookService.update(id, book);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
