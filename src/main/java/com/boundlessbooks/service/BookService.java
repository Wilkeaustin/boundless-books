package com.boundlessbooks.service;

import com.boundlessbooks.model.Book;
import com.boundlessbooks.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Contains the main rules for finding and browsing books.
 */
@Service
public class BookService {

	private final BookRepository books;

	/** @param books repository used to read book records */
	public BookService(BookRepository books) {
		this.books = books;
	}

	/**
	 * Returns books using an optional title search or genre filter.
	 *
	 * @param search optional title search
	 * @param genre optional genre filter
	 * @return matching books or all books when no filter is supplied
	 */
	public List<Book> browse(String search, String genre) {
		if (search != null && !search.isBlank()) {
			return books.findByTitleContainingIgnoreCase(search);
		}

		if (genre != null && !genre.isBlank()) {
			return books.findByGenreIgnoreCase(genre);
		}

		return books.findAll();
	}

	/**
	 * Finds a book by its ID.
	 *
	 * @param id book ID
	 * @return matching book
	 * @throws IllegalArgumentException if the book does not exist
	 */
	public Book findById(Long id) {
		return books.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Book not found"));
	}
}
