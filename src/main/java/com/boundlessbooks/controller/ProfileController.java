package com.boundlessbooks.controller;

import com.boundlessbooks.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	private final UserService users;
	private final ReviewService reviews;

	public ProfileController(UserService users, ReviewService reviews) {
		this.users = users;
		this.reviews = reviews;
	}

	@GetMapping("/profile")
	public String profile(Authentication auth, Model model) {
		model.addAttribute("user", users.findByUsername(auth.getName()));
		model.addAttribute("reviews", reviews.forUser(auth.getName()));
		return "profile";
	}
}
