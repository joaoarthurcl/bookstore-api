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
    public ResponseEntity<Book> findById(@PathVariable Integer id) {
        Book obj = bookService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        final var books = this.bookService.findAll();
        final var booksDTO = books.stream().map(BookDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(booksDTO);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        book = this.bookService.create(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        final var obj = this.bookService.update(id, bookDTO);
        return ResponseEntity.ok().body(new BookDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
