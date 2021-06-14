package com.jlee.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jlee.bookstore.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Field title is required!")
    @Length(min = 3, max = 50, message = "Min = 3 and Max = 50")
    private String title;

    @NotNull(message = "Field author_name is required!")
    @Length(min = 3, max = 50, message = "Min = 3 and Max = 50")
    private String author_name;

    @NotNull(message = "Field description is required!")
    @Length(min = 3, max = 2000000, message = "Min = 3 and Max = 2000000")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Book ofDTO(final BookDTO bookDTO) {
        return com.jlee.bookstore.domain.Book.builder()
                .title(bookDTO.getTitle())
                .build();
    }

}
