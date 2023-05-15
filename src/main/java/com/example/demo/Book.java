package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il titolo non può essere vuoto")
    private String title;

    @NotBlank(message = "L'autore non può essere vuoto")
    private String author;

    @NotBlank(message = "Il codice ISBN non può essere vuoto")
    @Column(unique = true)
    private String isbn;

    @NotBlank(message = "La data di aggiunta non può essere vuota")
    private LocalDate dateAdded;

    private LocalDate dateDeleted;

    private String plot;

    @Column(nullable = false)
    private Integer numberOfReads = 0;

    // Costruttori, getter e setter

    // ...

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(LocalDate dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getNumberOfReads() {
        return numberOfReads;
    }

    public void setNumberOfReads(Integer numberOfReads) {
        this.numberOfReads = numberOfReads;
    }
}