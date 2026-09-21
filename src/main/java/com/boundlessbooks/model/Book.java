package com.boundlessbooks.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book displayed in the Boundless Books catalog.
 */
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String author;
	private String genre;
	@Column(unique = true)
	private String isbn;
	@Column(length = 2000)
	private String description;
	private LocalDate publicationDate;
	private String coverImage;
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@OrderBy("reviewDate DESC")
	private List<Review> reviews = new ArrayList<>();

	/** @return database ID of the book */
	public Long getId() {
		return id;
	}

	/** @param id database ID of the book */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return title of the book */
	public String getTitle() {
		return title;
	}

	/** @param title title of the book */
	public void setTitle(String title) {
		this.title = title;
	}

	/** @return name of the book's author */
	public String getAuthor() {
		return author;
	}

	/** @param author name of the book's author */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** @return genre assigned to the book */
	public String getGenre() {
		return genre;
	}

	/** @param genre genre assigned to the book */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/** @return ISBN of the book */
	public String getIsbn() {
		return isbn;
	}

	/** @param isbn ISBN of the book */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/** @return description of the book */
	public String getDescription() {
		return description;
	}

	/** @param description description of the book */
	public void setDescription(String description) {
		this.description = description;
	}

	/** @return original publication date */
	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	/** @param publicationDate original publication date */
	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	/** @return path to the cover image */
	public String getCoverImage() {
		return coverImage;
	}

	/** @param coverImage path to the cover image */
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	/** @return reviews written for the book */
	public List<Review> getReviews() {
		return reviews;
	}

	/** @param reviews reviews written for the book */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Calculates the average of all ratings for the book.
	 *
	 * @return average rating, or zero when the book has no reviews
	 */
	public double getAverageRating() {
		return reviews.isEmpty() ? 0 : reviews.stream().mapToInt(Review::getRating).average().orElse(0);
	}
}
