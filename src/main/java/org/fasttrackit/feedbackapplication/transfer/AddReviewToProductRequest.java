package org.fasttrackit.feedbackapplication.transfer;

import javax.validation.constraints.NotNull;

public class AddReviewToProductRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long reviewId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
}
