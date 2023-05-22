package com.example.demo.controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.specifications.BookSpecifications;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import jakarta.persistence.criteria.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
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
        Specification<Book> spec = Specification.where(BookSpecifications.dateDeletedIsNull());
    
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

                if (bookDetails.getTitle() != null) {
                    book.setTitle(bookDetails.getTitle());
                }
            
                if (bookDetails.getAuthor() != null) {
                    book.setAuthor(bookDetails.getAuthor());
                }
            
                if (bookDetails.getIsbn() != null) {
                    book.setIsbn(bookDetails.getIsbn());
                }
                        
                if (bookDetails.getPlot() != null) {
                    book.setPlot(bookDetails.getPlot());
                }

                if (bookDetails.getNumberOfReads() != null) {
                    book.setNumberOfReads(bookDetails.getNumberOfReads());
                  }
        return bookRepository.save(book);
    }

    // PUT /books/{id}/read
    @PutMapping("/{id}/read")
    public Book readBook(@PathVariable long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.read();

        return bookRepository.save(book);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        book.setDateDeleted(LocalDate.now());
        return bookRepository.save(book);
    }
}