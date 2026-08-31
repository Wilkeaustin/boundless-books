package com.boundlessbooks.repository;

import com.boundlessbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleIgnoreCase(String title, String author);

	List<Book> findByGenreIgnoreCase(String genre);
}
