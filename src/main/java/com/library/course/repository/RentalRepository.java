package com.library.course.repository;

import com.library.course.entity.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByCustomer_Id(Long id);
    Page<Rental> findAll(Pageable pageable);

    //Query SQL
    @Query(value = "SELECT * FROM rental" +
            " JOIN book  AS b ON rental.book_id = b.id" +
            " WHERE b.title = :titolo", nativeQuery = true)
    Page<Rental> findRentalsFiltered(@Param("titolo") String titolo, Pageable pageable);

    //Query HQL
    /*@Query( "select r from Rental r " +
            "where r.book.titolo = ?1" )
    Page < Rental > findByBook_Titolo( String titolo, Pageable pageable );*/
    @Query(value = "SELECT r FROM Rental r" +
            " JOIN r.book b" +
            " WHERE b.titolo = :titolo")
    Page<Rental> findRentalsFilteredFalse(@Param("titolo") String titolo, Pageable pageable);
}


/*
SELECT * FROM rental
JOIN book  AS b ON rental.id = book.id
WHERE b.title = :titolo

 */