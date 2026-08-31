package com.boundlessbooks.config;

import com.boundlessbooks.model.Book;
import com.boundlessbooks.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
	@Bean
	CommandLineRunner seedBooks(BookRepository books) {
		return args -> {
			if (books.count() > 0)
				return;
			books.save(make("Atomic Habits", "James Clear", "Self-Help", "9780735211292",
					"An easy and proven way to build good habits and break bad ones through small, practical changes.",
					LocalDate.of(2018, 10, 16), "/images/atomichabits.jpg"));
			books.save(make("The Richest Man in Babylon", "George S. Clason", "Finance", "9780451205360",
					"Classic financial lessons told through simple stories set in ancient Babylon.",
					LocalDate.of(1926, 1, 1), "/images/richestman.jpg"));
			books.save(make("Switch On Your Brain", "Caroline Leaf", "Health", "9780801018398",
					"A look at how thought patterns can influence choices, habits, and personal well-being.",
					LocalDate.of(2013, 9, 1), "/images/switchbrain.jpg"));
		};
	}

	private Book make(String title, String author, String genre, String isbn, String description, LocalDate date,
			String image) {
		Book b = new Book();
		b.setTitle(title);
		b.setAuthor(author);
		b.setGenre(genre);
		b.setIsbn(isbn);
		b.setDescription(description);
		b.setPublicationDate(date);
		b.setCoverImage(image);
		return b;
	}
}
