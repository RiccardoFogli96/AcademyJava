package com.donato.esercizio.esercizio26092024.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateAuthorDTO {

    private String name;
    private String surname;
    private String biography;
}
