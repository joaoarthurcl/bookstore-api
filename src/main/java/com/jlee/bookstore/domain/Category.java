package com.jlee.bookstore.domain;

import com.jlee.bookstore.dto.CategoryDTO;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    public static final long serialVersionUID = 5024048380744116098L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Field name is required!")
    @Length(min = 3, max = 100, message = "Min = 3 and Max = 100")
    private String name;

    @NotNull(message = "Field description is required!")
    @Length(min = 3, max = 200, message = "Min = 3 and Max = 200")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Category ofDTO(final CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }
}
