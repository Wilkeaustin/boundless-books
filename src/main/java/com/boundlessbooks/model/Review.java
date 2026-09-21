package com.boundlessbooks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Represents a rating and written review submitted by a user.
 */
@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	@Min(1)
	@Max(5)
	private int rating;
	@NotBlank
	@Column(length = 2000)
	private String reviewText;
	private LocalDateTime reviewDate;

	/** Sets the review date before a new review is saved. */
	@PrePersist
	void setDate() {
		if (reviewDate == null)
			reviewDate = LocalDateTime.now();
	}

	/** @return database ID of the review */
	public Long getId() {
		return id;
	}

	/** @param id database ID of the review */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return user who wrote the review */
	public User getUser() {
		return user;
	}

	/** @param user user who wrote the review */
	public void setUser(User user) {
		this.user = user;
	}

	/** @return book connected to the review */
	public Book getBook() {
		return book;
	}

	/** @param book book connected to the review */
	public void setBook(Book book) {
		this.book = book;
	}

	/** @return rating from one to five */
	public int getRating() {
		return rating;
	}

	/** @param rating rating from one to five */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/** @return written review text */
	public String getReviewText() {
		return reviewText;
	}

	/** @param reviewText written review text */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	/** @return date and time the review was created */
	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	/** @param reviewDate date and time the review was created */
	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}
}
