package org.fasttrackit.feedbackapplication.service;

import org.fasttrackit.feedbackapplication.domain.Product;
import org.fasttrackit.feedbackapplication.domain.Review;
import org.fasttrackit.feedbackapplication.exception.ResourceNotFoundException;
import org.fasttrackit.feedbackapplication.persistance.ProductRepository;
import org.fasttrackit.feedbackapplication.transfer.AddReviewToProductRequest;
import org.fasttrackit.feedbackapplication.transfer.ProductResponse;
import org.fasttrackit.feedbackapplication.transfer.SaveProductRequest;
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
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ReviewService reviewService;

    @Autowired
    public ProductService(ProductRepository productRepository, ReviewService reviewService) {
        this.productRepository = productRepository;
        this.reviewService = reviewService;
    }

    public void addReviewToProduct(AddReviewToProductRequest request){

        LOGGER.info("Adding review to product.");

        Product product = productRepository.findById(request.getProductId()).orElse(new Product());

        Review review = reviewService.getReview(request.getReviewId());

        product.addReviewToProduct(review);
        productRepository.save(product);

    }

    public Product createProduct(SaveProductRequest request){
        LOGGER.info("Creating product {}", request);
        Product product = new Product();
        product.setName(request.getName());

        return productRepository.save(product);
    }

    public Product getProduct(long id) {

        LOGGER.info("Retrieving product {}", id);
        //using optional
        return productRepository.findById(id)
                //lambda expression
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + id + " does not exist."));
    }

    @Transactional
    public Page<ProductResponse> getProducts(ProductResponse request, Pageable pageable) {
        LOGGER.info("Retrieving Products: {}", request);

        Page<Product> products;
        products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products.getContent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());

            productResponses.add(productResponse);
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }


    public Product updateProduct(long id, SaveProductRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);

        Product product = getProduct(id);

        BeanUtils.copyProperties(request, product);

        return productRepository.save(product);

    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);

    }
}
