
package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.ProductService;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    // Search products by name
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    // Filter products by category
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(
            @PathVariable Integer categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    // Sort products by price
    @GetMapping("/sort")
    public List<Product> sortProductsByPrice(
            @RequestParam(defaultValue = "asc") String direction) {
        return productService.sortProductsByPrice(direction);
    }

    // Pagination
    @GetMapping("/page")
    public Page<Product> getProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return productService.getProductsPaginated(page, size);
    }

    // Filter products by price range
    @GetMapping("/filter/price")
    public List<Product> filterProductsByPrice(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }

    // Combined search, filtering, sorting and pagination
    @GetMapping("/filter")
    public Page<Product> searchAndFilterProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return productService.searchAndFilterProducts(
                keyword, categoryId, minPrice, maxPrice,
                page, size, sortDirection);
    }
}
