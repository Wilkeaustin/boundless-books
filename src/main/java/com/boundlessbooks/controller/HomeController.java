package com.boundlessbooks.controller;

import com.boundlessbooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	private final BookService books;

	public HomeController(BookService books) {
		this.books = books;
	}

	@GetMapping({ "/", "/books" })
	public String browse(@RequestParam(required = false) String search, @RequestParam(required = false) String genre,
			Model model) {
		model.addAttribute("books", books.browse(search, genre));
		model.addAttribute("search", search);
		model.addAttribute("genre", genre);
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
