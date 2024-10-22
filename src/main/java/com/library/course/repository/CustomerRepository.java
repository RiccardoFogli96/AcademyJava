package com.library.course.repository;

import com.library.course.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository< Customer, Long > {
    Customer findByEmailAndPassword(String email, String password);
}
