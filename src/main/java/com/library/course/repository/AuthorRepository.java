package com.library.course.repository;

import com.library.course.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
	List<Author> findAllByIdIn(List<Long> ids);
}
