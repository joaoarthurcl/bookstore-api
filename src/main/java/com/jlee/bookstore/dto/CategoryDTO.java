package com.jlee.bookstore.dto;

import com.jlee.bookstore.domain.Category;
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
public class CategoryDTO implements Serializable {
    public static final long serialVersionUID = 8857942005721366061L;

    private Integer id;
    private String name;
    private String description;

    public CategoryDTO(Category category) {
        super();
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
