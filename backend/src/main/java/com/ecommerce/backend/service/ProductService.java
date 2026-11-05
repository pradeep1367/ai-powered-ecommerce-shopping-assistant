
package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Integer productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with ID: " + productId
                ));
    }

    // Search products by name
    public List<Product> searchProducts(String keyword) {
        return productRepository
                .findByProductNameContainingIgnoreCase(keyword);
    }

    // Filter products by category
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    // Sort products by price
    public List<Product> sortProductsByPrice(String direction) {

        if ("asc".equalsIgnoreCase(direction)) {
            return productRepository.findAllByOrderByPriceAsc();
        }

        if ("desc".equalsIgnoreCase(direction)) {
            return productRepository.findAllByOrderByPriceDesc();
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid sort direction. Use asc or desc."
        );
    }

    // Pagination
    public Page<Product> getProductsPaginated(int page, int size) {

        if (page < 0 || size < 1 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid page or size."
            );
        }

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }

    // Filter products by price range
    public List<Product> filterProductsByPrice(
            BigDecimal minPrice, BigDecimal maxPrice) {

        if (minPrice.compareTo(BigDecimal.ZERO) < 0
                || maxPrice.compareTo(minPrice) < 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid price range."
            );
        }

        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Combined search, filtering, sorting and pagination
    public Page<Product> searchAndFilterProducts(
            String keyword,
            Integer categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sortDirection) {

        if (page < 0 || size < 1 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid page or size."
            );
        }

        if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Minimum price cannot be negative."
            );
        }

        if (minPrice != null && maxPrice != null
                && maxPrice.compareTo(minPrice) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Maximum price must be greater than minimum price."
            );
        }

        Sort.Direction direction;

        try {
            direction = Sort.Direction.fromString(sortDirection);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sort direction must be asc or desc."
            );
        }

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(direction, "price"));

        return productRepository.searchAndFilterProducts(
                keyword, categoryId, minPrice, maxPrice, pageable);
    }
}
