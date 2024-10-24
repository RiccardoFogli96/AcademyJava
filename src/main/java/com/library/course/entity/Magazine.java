package com.library.course.entity;

import com.library.course.model.TipologyMagazine;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name="tipology")
    private TipologyMagazine tipologyMagazine;
    @Column(name="published_date")
    private LocalDate publishedDate;
    @Column(name = "number_magazine")
    private int numberMagazine;

    @OneToMany(mappedBy = "magazineId")
    List<Author> authorList;
}