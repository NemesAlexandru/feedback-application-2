package org.fasttrackit.feedbackapplication.persistance;

import org.fasttrackit.feedbackapplication.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
