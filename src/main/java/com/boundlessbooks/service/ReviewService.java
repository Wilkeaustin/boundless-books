package com.boundlessbooks.service;

import com.boundlessbooks.model.*;
import com.boundlessbooks.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Handles the creation and lookup of book reviews.
 */
@Service
public class ReviewService {
	private final ReviewRepository reviews;
	private final BookService books;
	private final UserService users;

	/**
	 * Creates the service with its required data services.
	 *
	 * @param reviews repository used to save and find reviews
	 * @param books service used to find books
	 * @param users service used to find users
	 */
	public ReviewService(ReviewRepository reviews, BookService books, UserService users) {
		this.reviews = reviews;
		this.books = books;
		this.users = users;
	}

	/**
	 * Connects a review to its book and author before saving it.
	 *
	 * @param bookId ID of the reviewed book
	 * @param review review to save
	 * @param username username of the review author
	 */
	public void create(Long bookId, Review review, String username) {
		review.setBook(books.findById(bookId));
		review.setUser(users.findByUsername(username));
		reviews.save(review);
	}

	/**
	 * Finds all reviews written by one user.
	 *
	 * @param username username to search for
	 * @return reviews written by the user
	 */
	public List<Review> forUser(String username) {
		return reviews.findByUserUsername(username);
	}
}
