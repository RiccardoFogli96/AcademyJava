package com.donato.esercizio.esercizio26092024.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookDTO {

    private Long id;

    private String titolo;

    private String descrizione;

    private Tipologia tipologia;

    private Long authorId;
}
