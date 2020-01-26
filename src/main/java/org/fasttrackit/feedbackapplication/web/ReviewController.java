package org.fasttrackit.feedbackapplication.web;

import org.fasttrackit.feedbackapplication.domain.Review;
import org.fasttrackit.feedbackapplication.service.ReviewService;
import org.fasttrackit.feedbackapplication.transfer.ProductResponse;
import org.fasttrackit.feedbackapplication.transfer.ReviewResponse;
import org.fasttrackit.feedbackapplication.transfer.SaveReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody @Valid SaveReviewRequest request) {
        Review review = reviewService.createReview(request);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Review> getReview(@PathVariable Long id) {
//        Review review = reviewService.getReview(id);
//        return new ResponseEntity<>(review, HttpStatus.OK);
//    }

//    @GetMapping
//    public ResponseEntity<Page<ReviewResponse>> getReviews(ReviewResponse request, Pageable pageable) {
//        Page<ReviewResponse> reviews = reviewService.getReviews(request, pageable);
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
//        }

    @GetMapping("/{id}")
    public ResponseEntity<Page<ReviewResponse>> getReviewsByProductId(@PathVariable Long id, Pageable pageable) {
        Page<ReviewResponse> reviews = reviewService.findAllByProductId(id, pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody @Valid SaveReviewRequest request) {
        Review review = reviewService.updateReview(id, request);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
