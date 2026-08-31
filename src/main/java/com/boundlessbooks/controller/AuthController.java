package com.boundlessbooks.controller;

import com.boundlessbooks.model.User;
import com.boundlessbooks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
	private final UserService users;

	public AuthController(UserService users) {
		this.users = users;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

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
