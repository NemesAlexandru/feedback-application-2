package org.fasttrackit.feedbackapplication.service;

import org.fasttrackit.feedbackapplication.domain.Product;
import org.fasttrackit.feedbackapplication.domain.Review;
import org.fasttrackit.feedbackapplication.exception.ResourceNotFoundException;
import org.fasttrackit.feedbackapplication.persistance.ReviewRepository;
import org.fasttrackit.feedbackapplication.transfer.ProductResponse;
import org.fasttrackit.feedbackapplication.transfer.ReviewResponse;
import org.fasttrackit.feedbackapplication.transfer.SaveReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(SaveReviewRequest request){
        LOGGER.info("Creating review {}", request);
        Review review = new Review();
        review.setReviewName(request.getReviewName());
        review.setDescription(request.getDescription());
        return reviewRepository.save(review);
    }

    public Review getReview(long id) {

        LOGGER.info("Retrieving review {}", id);
        //using optional
        return reviewRepository.findById(id)
                //lambda expression
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review " + id + " does not exist."));
    }

    @Transactional
    public Page<ReviewResponse> getReviews(ReviewResponse request, Pageable pageable) {
        LOGGER.info("Retrieving Reviews: {}", request);

        Page<Review> reviews;
        reviews = reviewRepository.findAll(pageable);
        List<ReviewResponse> reviewResponses = new ArrayList<>();

        for (Review review : reviews.getContent()) {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setId(review.getId());
            reviewResponse.setDescription(review.getDescription());
            reviewResponse.setReviewName(review.getReviewName());

            reviewResponses.add(reviewResponse);
        }
        return new PageImpl<>(reviewResponses, pageable, reviews.getTotalElements());
    }

    public Review updateReview(long id, SaveReviewRequest request) {
        LOGGER.info("Updating review {}: {}", id, request);

        Review review = getReview(id);

        BeanUtils.copyProperties(request, review);

        return reviewRepository.save(review);

    }

    public void deleteReview(long id) {
        LOGGER.info("Deleting review {}", id);
        reviewRepository.deleteById(id);
        LOGGER.info("Deleted review {}", id);

    }
}
