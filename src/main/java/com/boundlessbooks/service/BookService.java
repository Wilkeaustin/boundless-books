package com.boundlessbooks.service;

import com.boundlessbooks.model.Book;
import com.boundlessbooks.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
	private final BookRepository books;

	public BookService(BookRepository books) {
		this.books = books;
	}

	public List<Book> browse(String search, String genre) {
		if (search != null && !search.isBlank())
			return books.findByTitleIgnoreCase(search, search);
		if (genre != null && !genre.isBlank())
			return books.findByGenreIgnoreCase(genre);
		return books.findAll();
	}

	public Book findById(Long id) {
		return books.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
	}
}
