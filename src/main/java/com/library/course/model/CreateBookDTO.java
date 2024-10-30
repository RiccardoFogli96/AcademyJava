package com.library.course.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@SuperBuilder

public class CreateBookDTO {

    @NotNull
    private String titolo;
    private String descrizione;
    @NotNull
    private GenreBook genreBook;
    @NotNull
    private Long authorId;

}
