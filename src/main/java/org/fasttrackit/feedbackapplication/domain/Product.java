package org.fasttrackit.feedbackapplication.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;

    private String imageUrl;

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    public void addReviewToProduct(Review review){

        reviews.add(review);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
