package com.boundlessbooks.controller;

import com.boundlessbooks.model.Review;
import com.boundlessbooks.service.BookService;
import com.boundlessbooks.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Handles book details and book review requests.
 */
@Controller
public class BookController {

	private final BookService books;
	private final ReviewService reviews;

	/**
	 * Creates the controller with its book and review services.
	 *
	 * @param books service used to find books
	 * @param reviews service used to create reviews
	 */
	public BookController(BookService books, ReviewService reviews) {
		this.books = books;
		this.reviews = reviews;
	}

	/**
	 * Displays one book and its reviews.
	 *
	 * @param id ID of the requested book
	 * @param model data sent to the page
	 * @return book details template name
	 */
	@GetMapping("/books/{id}")
	public String details(@PathVariable Long id, Model model) {
		model.addAttribute("book", books.findById(id));
		model.addAttribute("review", new Review());
		return "book-details";
	}

	/**
	 * Validates and saves a review for a book.
	 *
	 * @param bookId ID of the reviewed book
	 * @param review review form values
	 * @param result validation results
	 * @param auth currently signed-in user
	 * @param model data returned when validation fails
	 * @return details page on error or a redirect after success
	 */
	@PostMapping("/books/{bookId}/reviews")
	public String review(
			@PathVariable Long bookId,
			@Valid @ModelAttribute Review review,
			BindingResult result,
			Authentication auth,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("book", books.findById(bookId));
			return "book-details";
		}

		reviews.create(bookId, review, auth.getName());
		return "redirect:/books/" + bookId + "?reviewed";
	}
}
