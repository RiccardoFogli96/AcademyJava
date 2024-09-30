package com.donato.esercizio.esercizio26092024.repository;

import com.donato.esercizio.esercizio26092024.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
