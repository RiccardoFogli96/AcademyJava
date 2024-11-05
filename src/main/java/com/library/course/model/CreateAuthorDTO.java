package com.library.course.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class CreateAuthorDTO {

    private String name;
    private String surname;
    private String biography;
}
