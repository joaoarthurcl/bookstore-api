package com.jlee.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jlee.bookstore.dto.BookDTO;
import com.jlee.bookstore.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book implements Serializable {
    public static final long serialVersionUID = 5024048380744116098L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String authorName;
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Book ofDTO(final BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .authorName(bookDTO.getAuthor_name())
                .description(bookDTO.getDescription())
                .build();
    }

}
