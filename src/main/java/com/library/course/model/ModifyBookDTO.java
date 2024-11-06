package com.library.course.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyBookDTO {
    private Long id;
    private String titolo;
}
