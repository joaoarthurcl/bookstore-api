package com.jlee.bookstore.controllers;

import com.jlee.bookstore.domain.Book;
import com.jlee.bookstore.domain.Category;
import com.jlee.bookstore.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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


}
