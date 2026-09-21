package com.boundlessbooks.controller;

import com.boundlessbooks.model.User;
import com.boundlessbooks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Handles the login and registration pages.
 */
@Controller
public class AuthController {
	private final UserService users;

	/**
	 * Creates the controller with its required user service.
	 *
	 * @param users service used to register users
	 */
	public AuthController(UserService users) {
		this.users = users;
	}

	/**
	 * Displays the login page.
	 *
	 * @return login template name
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Displays an empty registration form.
	 *
	 * @param model data sent to the page
	 * @return registration template name
	 */
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	/**
	 * Validates and saves a new user account.
	 *
	 * @param user registration form values
	 * @param result validation results
	 * @param model data sent back when registration fails
	 * @return registration page on error or login page after success
	 */
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (result.hasErrors())
			return "register";
		try {
			users.register(user);
		} catch (IllegalArgumentException ex) {
			model.addAttribute("registrationError", ex.getMessage());
			return "register";
		}
		return "redirect:/login?registered";
	}
}
