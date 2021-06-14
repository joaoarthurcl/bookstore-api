package com.jlee.bookstore.dto;

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

    public BookDTO(com.jlee.bookstore.domain.Book book) {
        super();
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public static BookDTO ofEntity(com.jlee.bookstore.domain.Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }
}
