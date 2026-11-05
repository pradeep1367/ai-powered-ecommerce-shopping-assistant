
package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Search products by name
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    // Filter products by category ID
    List<Product> findByCategory_CategoryId(Integer categoryId);

    // Sort products by price
    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    // Filter products within a price range
    List<Product> findByPriceBetween(
            BigDecimal minPrice, BigDecimal maxPrice);

    // Combined search and filter
    @Query("""
        SELECT p FROM Product p
        WHERE (:keyword IS NULL OR
               LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND (:categoryId IS NULL OR
             p.category.categoryId = :categoryId)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        """)
    Page<Product> searchAndFilterProducts(
            @Param("keyword") String keyword,
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );
}
