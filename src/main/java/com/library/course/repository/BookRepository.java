package com.library.course.repository;

import com.library.course.entity.Book;
import com.library.course.model.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
	List<Book> findByTipologia(GenreBook genreBook);
	List<Book> findByAuthorId(Long authorId);
	Book findByAuthorIdAndId(Long authorId, Long bookId);
	void deleteByAuthorId(Long authorId);
	boolean existsByAuthor_IdAndTitolo(Long authorId, String title);
}
