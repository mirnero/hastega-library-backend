package com.example.demo.controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import jakarta.persistence.criteria.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // GET /books
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        Specification<Book> spec = Specification.where(null);
    
        if (search != null && !search.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                String lowerCaseSearch = search.toLowerCase();
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + lowerCaseSearch + "%");
                Predicate authorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + lowerCaseSearch + "%");
                Predicate isbnPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("isbn")), "%" + lowerCaseSearch + "%");
                return criteriaBuilder.or(titlePredicate, authorPredicate, isbnPredicate);
            });
        }
    
        return bookRepository.findAll(spec, pageable);
    }


    // GET /books/{id}
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    // POST /books
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setDateAdded(LocalDate.now());
        return bookRepository.save(book);
    }

    // PUT /books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setDateAdded(bookDetails.getDateAdded());
        book.setDateDeleted(bookDetails.getDateDeleted());
        book.setPlot(bookDetails.getPlot());
        book.setNumberOfReads(bookDetails.getNumberOfReads());

        return bookRepository.save(book);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookRepository.delete(book);
    }
}