package org.fasttrackit.feedbackapplication.transfer;

import javax.validation.constraints.NotNull;

public class ReviewResponse {

    private Long id;
    private String reviewName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "id=" + id +
                ", reviewName='" + reviewName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
