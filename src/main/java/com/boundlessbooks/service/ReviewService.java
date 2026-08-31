package com.boundlessbooks.service;

import com.boundlessbooks.model.*;
import com.boundlessbooks.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
	private final ReviewRepository reviews;
	private final BookService books;
	private final UserService users;

	public ReviewService(ReviewRepository reviews, BookService books, UserService users) {
		this.reviews = reviews;
		this.books = books;
		this.users = users;
	}

	public void create(Long bookId, Review review, String username) {
		review.setBook(books.findById(bookId));
		review.setUser(users.findByUsername(username));
		reviews.save(review);
	}

	public List<Review> forUser(String username) {
		return reviews.findByUserUsername(username);
	}
}
