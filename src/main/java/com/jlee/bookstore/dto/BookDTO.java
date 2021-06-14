package com.jlee.bookstore.dto;

import com.jlee.bookstore.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    public static final long serialVersionUID = 8857942005721366061L;

    private Integer id;
    private String title;
    private String author_name;
    private String description;

    public BookDTO(Book book) {
        super();
        this.id = book.getId();
        this.title = book.getTitle();
        this.author_name = book.getAuthorName();
        this.description = book.getDescription();
    }
}
