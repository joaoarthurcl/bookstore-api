package com.jlee.bookstore.service;

import com.jlee.bookstore.domain.Book;
import com.jlee.bookstore.domain.Category;
import com.jlee.bookstore.repositories.BookRepository;
import com.jlee.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public void instantiateDatabase() {
        Category category = new Category(null, "Info", "IT Books");
        Category category2 = new Category(null, "sci-fi", "sci-fi Books");
        Category category3 = new Category(null, "Bio", "Bio Books");

        Book book = new Book(null, "Clean Code", "Robert Martin", "lorem ipsum", category);
        Book book2 = new Book(null, "Clean Code2", "Robert Martin", "lorem ipsum", category2);
        Book book3 = new Book(null, "Clean Code3", "Robert Martin", "lorem ipsum", category3);
        Book book4 = new Book(null, "Clean Code4", "Robert Martin", "lorem ipsum", category);
        Book book5 = new Book(null, "Clean Code5", "Robert Martin", "lorem ipsum", category2);

        category.getBooks().addAll(Arrays.asList(book, book4));
        category2.getBooks().addAll(Arrays.asList(book2, book5));
        category3.getBooks().addAll(Collections.singletonList(book3));

        categoryRepository.saveAll(Arrays.asList(category, category2, category3));
        bookRepository.saveAll(Arrays.asList(book, book2, book3, book4, book5));


    }
}
