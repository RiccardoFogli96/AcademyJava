package com.donato.esercizio.esercizio26092024.repository;

import com.donato.esercizio.esercizio26092024.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
