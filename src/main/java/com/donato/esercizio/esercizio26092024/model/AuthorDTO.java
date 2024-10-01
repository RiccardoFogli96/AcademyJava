package com.donato.esercizio.esercizio26092024.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuthorDTO extends CreateAuthorDTO{

    private Long id;

    public AuthorDTO(String name, String surname, String biography) {
        super(name,surname,biography);
    }
}
