package com.boundlessbooks.controller;

import com.boundlessbooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles the main browsing and information pages.
 */
@Controller
public class HomeController {
	private final BookService books;

	/**
	 * Creates the controller with its required book service.
	 *
	 * @param books service used to browse books
	 */
	public HomeController(BookService books) {
		this.books = books;
	}

	/**
	 * Displays all books or filters them by title or genre.
	 *
	 * @param search optional title search
	 * @param genre optional genre filter
	 * @param model data sent to the page
	 * @return home page template name
	 */
	@GetMapping({ "/", "/books" })
	public String browse(@RequestParam(required = false) String search, @RequestParam(required = false) String genre,
			Model model) {
		model.addAttribute("books", books.browse(search, genre));
		model.addAttribute("search", search);
		model.addAttribute("genre", genre);
		return "index";
	}

	/** @return about page template name */
	@GetMapping("/about")
	public String about() {
		return "about";
	}

	/** @return contact page template name */
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
