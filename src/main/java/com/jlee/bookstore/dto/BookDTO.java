package com.jlee.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    public static final long serialVersionUID = 8857942005721366061L;

    private Integer id;

    @NotNull(message = "Field title is required!")
    @Length(min = 3, max = 50, message = "Min = 3 and Max = 50")
    private String title;

    @NotNull(message = "Field author_name is required!")
    @Length(min = 3, max = 50, message = "Min = 3 and Max = 50")
    private String author_name;

    @NotNull(message = "Field description is required!")
    @Length(min = 3, max = 2000000, message = "Min = 3 and Max = 2000000")
    private String description;

    public BookDTO(com.jlee.bookstore.domain.Book book) {
        super();
        this.id = book.getId();
        this.title = book.getTitle();
        this.author_name = book.getAuthor_name();
        this.description = book.getDescription();
    }

    public static BookDTO ofEntity(com.jlee.bookstore.domain.Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author_name(book.getAuthor_name())
                .description(book.getDescription())
                .build();
    }
}
