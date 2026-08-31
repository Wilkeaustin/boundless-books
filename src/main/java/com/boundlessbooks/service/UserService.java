package com.boundlessbooks.service;

import com.boundlessbooks.model.User;
import com.boundlessbooks.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	private final UserRepository users;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
		this.users = users;
		this.passwordEncoder = passwordEncoder;
	}

	public User register(User user) {
		if (users.existsByUsername(user.getUsername()))
			throw new IllegalArgumentException("That username is already taken.");
		if (users.existsByEmail(user.getEmail()))
			throw new IllegalArgumentException("That email is already registered.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return users.save(user);
	}

	public User findByUsername(String username) {
		return users.findByUsername(username).orElseThrow();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles("USER").build();
	}
}
