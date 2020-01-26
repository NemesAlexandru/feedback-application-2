package org.fasttrackit.feedbackapplication.persistance;

import org.fasttrackit.feedbackapplication.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByProductId (Long productId, Pageable pageable);
}
