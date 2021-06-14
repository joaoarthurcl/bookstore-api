package com.jlee.bookstore.dto;

import com.jlee.bookstore.domain.Category;
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
public class CategoryDTO implements Serializable {
    public static final long serialVersionUID = 8857942005721366061L;

    private Integer id;

    @NotNull(message = "Field name is required!")
    @Length(min = 3, max = 100, message = "Min = 3 and Max = 100")
    private String name;

    @NotNull(message = "Field description is required!")
    @Length(min = 3, max = 200, message = "Min = 3 and Max = 200")
    private String description;

    public CategoryDTO(Category category) {
        super();
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public static CategoryDTO ofDTO(final Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
