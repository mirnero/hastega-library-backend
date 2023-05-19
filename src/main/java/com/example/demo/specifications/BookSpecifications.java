package com.example.demo.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Book;

public class BookSpecifications {

    public static Specification<Book> dateDeletedIsNull() {
        return (root, query, builder) -> builder.isNull(root.get("dateDeleted"));
    }
}