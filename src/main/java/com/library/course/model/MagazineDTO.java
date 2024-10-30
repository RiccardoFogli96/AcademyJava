package com.library.course.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MagazineDTO {

	private Long id;
	private String title;
	private TipologyMagazine tipologyMagazine;
	private LocalDate publishedDate;
	private int numberMagazine;

	List <AuthorDTO> authorList;
}
