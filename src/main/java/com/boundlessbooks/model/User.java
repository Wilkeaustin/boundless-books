package com.boundlessbooks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a registered Boundless Books user.
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	@NotBlank
	@Size(min = 4, max = 30)
	@Column(unique = true)
	private String username;
	@NotBlank
	@Size(min = 6)
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	/** @return database ID of the user */
	public Long getId() {
		return id;
	}

	/** @param id database ID of the user */
	public void setId(Long id) {
		this.id = id;
	}

	/** @return user's first name */
	public String getFirstName() {
		return firstName;
	}

	/** @param firstName user's first name */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** @return user's last name */
	public String getLastName() {
		return lastName;
	}

	/** @param lastName user's last name */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** @return user's email address */
	public String getEmail() {
		return email;
	}

	/** @param email user's email address */
	public void setEmail(String email) {
		this.email = email;
	}

	/** @return username used to sign in */
	public String getUsername() {
		return username;
	}

	/** @param username username used to sign in */
	public void setUsername(String username) {
		this.username = username;
	}

	/** @return encoded account password */
	public String getPassword() {
		return password;
	}

	/** @param password account password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** @return reviews written by the user */
	public List<Review> getReviews() {
		return reviews;
	}

	/** @param reviews reviews written by the user */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
