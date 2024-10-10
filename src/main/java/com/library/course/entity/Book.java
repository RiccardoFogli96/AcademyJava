package com.library.course.entity;

import com.library.course.model.Tipologia;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "book")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="title", nullable= false)
    private String titolo;

    @Setter
    @Column(name="description")
    private String descrizione;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="book_type")
    private Tipologia tipologia;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Rental> rentals;

}
