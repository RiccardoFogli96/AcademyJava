package com.library.course.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "biography")
    private String biography;

    @OneToMany(mappedBy = "author")
    List<Book> bookList;

    public Author(String name, String surname, String biography) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}
/*
Entità:
Author: rappresenta un autore con attributi come id, name, biography.

AuthorController:
Aggiungere un nuovo autore.
Ottenere tutti gli autori.

AuthorService --> Da creare
 */
