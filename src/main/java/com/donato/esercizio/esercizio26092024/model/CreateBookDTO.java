package com.donato.esercizio.esercizio26092024.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class CreateBookDTO {
    @NotNull
    private String titolo;
    private String descrizione;
    @NotNull
    private Tipologia tipologia;
    private Long authorId;

}
