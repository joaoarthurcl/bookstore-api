package com.jlee.bookstore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {
    public static final long serialVersionUID = -2133338592139267184L;

    private String fieldName;
    private String message;

}
