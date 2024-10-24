package com.library.course.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMagazineDTO {
    private String title;
    private TipologyMagazine tipologyMagazine;
    private LocalDate publishedDate;
    private int numberMagazine;
    List<Long> authorIdList;
}
