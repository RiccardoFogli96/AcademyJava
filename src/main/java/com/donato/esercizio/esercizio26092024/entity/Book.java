package com.donato.esercizio.esercizio26092024.entity;

import com.donato.esercizio.esercizio26092024.model.Tipologia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "book")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="title", nullable= false)
    private String titolo;

    @Setter
    @Column(name="description")
    private String descrizione;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="book_type")
    private Tipologia tipologia;

    @Setter
    @Column(name= "author_id")
    private Long authorId;

}
