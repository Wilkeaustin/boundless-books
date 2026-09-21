package com.boundlessbooks.service;

import com.boundlessbooks.model.User;
import com.boundlessbooks.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles registration and loads users for Spring Security.
 */
@Service
public class UserService implements UserDetailsService {
	private final UserRepository users;
	private final PasswordEncoder passwordEncoder;

	/**
	 * Creates the service with its repository and password encoder.
	 *
	 * @param users repository used to store user accounts
	 * @param passwordEncoder encoder used to protect passwords
	 */
	public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
		this.users = users;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Checks for duplicate account details and saves a new user.
	 *
	 * @param user account to register
	 * @return saved user account
	 * @throws IllegalArgumentException if the username or email is already used
	 */
	public User register(User user) {
		if (users.existsByUsername(user.getUsername()))
			throw new IllegalArgumentException("That username is already taken.");
		if (users.existsByEmail(user.getEmail()))
			throw new IllegalArgumentException("That email is already registered.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return users.save(user);
	}

	/**
	 * Finds a user by username.
	 *
	 * @param username username to search for
	 * @return matching user
	 */
	public User findByUsername(String username) {
		return users.findByUsername(username).orElseThrow();
	}

	/**
	 * Converts the stored user into the format required by Spring Security.
	 *
	 * @param username username to load
	 * @return security details for the user
	 * @throws UsernameNotFoundException if the account does not exist
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles("USER").build();
	}
}
