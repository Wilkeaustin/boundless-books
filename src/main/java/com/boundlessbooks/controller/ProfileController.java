package com.boundlessbooks.controller;

import com.boundlessbooks.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Displays account information and reviews for the signed-in user.
 */
@Controller
public class ProfileController {
	private final UserService users;
	private final ReviewService reviews;

	/**
	 * Creates the controller with its user and review services.
	 *
	 * @param users service used to find the current user
	 * @param reviews service used to find the user's reviews
	 */
	public ProfileController(UserService users, ReviewService reviews) {
		this.users = users;
		this.reviews = reviews;
	}

	/**
	 * Displays the current user's profile.
	 *
	 * @param auth currently signed-in user
	 * @param model data sent to the page
	 * @return profile template name
	 */
	@GetMapping("/profile")
	public String profile(Authentication auth, Model model) {
		model.addAttribute("user", users.findByUsername(auth.getName()));
		model.addAttribute("reviews", reviews.forUser(auth.getName()));
		return "profile";
	}
}
