package com.jlee.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category implements Serializable {
    public static final long serialVersionUID = 5024048380744116098L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
