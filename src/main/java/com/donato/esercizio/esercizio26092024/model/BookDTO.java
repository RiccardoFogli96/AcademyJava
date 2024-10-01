package com.donato.esercizio.esercizio26092024.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    private String titolo;

    private String descrizione;

    private Tipologia tipologia;

    private Long authorId;
}
