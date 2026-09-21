package com.boundlessbooks.repository;

import com.boundlessbooks.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Provides database access for review records.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
	/** @return reviews written by the supplied username */
	List<Review> findByUserUsername(String username);
}
