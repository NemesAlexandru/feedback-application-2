package org.fasttrackit.feedbackapplication.transfer;

import javax.validation.constraints.NotNull;

public class SaveReviewRequest {

    @NotNull
    private String reviewName;
    @NotNull
    private String description;

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
        return "SaveReviewRequest{" +
                "reviewName='" + reviewName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
