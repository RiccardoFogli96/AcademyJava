package com.library.course.model;

import jakarta.validation.constraints.NotNull;
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
public class BookDTO extends CreateBookDTO{

    private Long id;

    public BookDTO(@NotNull String titolo, String descrizione, @NotNull Tipologia tipologia, Long authorId, Long id) {
        super(titolo, descrizione, tipologia, authorId);
        this.id = id;
    }
}
