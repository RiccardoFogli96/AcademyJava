package com.library.course.repository;

import com.library.course.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository< Customer, Long > {
    Customer findByEmailAndPassword(String email, String password);
    Customer findByEmail(String email);
}
