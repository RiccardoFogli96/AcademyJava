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
    @Column(name="titolo", nullable= false)
    private String titolo;

    @Setter
    @Column(name="descrizione")
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(name="tipologia")
    private Tipologia tipologia;

}
