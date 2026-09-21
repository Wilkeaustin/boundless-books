package com.boundlessbooks.repository;

import com.boundlessbooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Provides database access for user accounts.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/** @return the matching user when one exists */
	Optional<User> findByUsername(String username);

	/** @return true when the username is already registered */
	boolean existsByUsername(String username);

	/** @return true when the email address is already registered */
	boolean existsByEmail(String email);
}
