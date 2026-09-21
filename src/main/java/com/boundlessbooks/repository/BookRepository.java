package com.boundlessbooks.repository;

import com.boundlessbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Provides database access for book records.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	/** @return books whose titles contain the supplied text */
	List<Book> findByTitleContainingIgnoreCase(String title);

	/** @return books that match the supplied genre */
	List<Book> findByGenreIgnoreCase(String genre);
}
