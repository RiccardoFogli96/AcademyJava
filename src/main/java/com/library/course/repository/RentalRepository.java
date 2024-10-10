package com.library.course.repository;

import com.library.course.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByCustomer_Id(Long id);
}
