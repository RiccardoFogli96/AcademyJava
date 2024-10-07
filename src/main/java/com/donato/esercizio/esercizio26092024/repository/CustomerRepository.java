package com.donato.esercizio.esercizio26092024.repository;

import com.donato.esercizio.esercizio26092024.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository< Customer, Long > {}
