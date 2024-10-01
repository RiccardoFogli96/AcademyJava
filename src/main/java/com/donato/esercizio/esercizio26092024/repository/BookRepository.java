package com.donato.esercizio.esercizio26092024.repository;

import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.model.Tipologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
	List <Book> findByTipologia( Tipologia tipologia);
}
