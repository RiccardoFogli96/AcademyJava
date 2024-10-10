package com.library.course.repository;

import com.library.course.entity.Book;
import com.library.course.model.Tipologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
	List<Book> findByTipologia(Tipologia tipologia);
	List<Book> findByAuthorId(Long authorId);
	Book findByAuthorIdAndId(Long authorId, Long bookId);
	void deleteByAuthorId(Long authorId);
}
