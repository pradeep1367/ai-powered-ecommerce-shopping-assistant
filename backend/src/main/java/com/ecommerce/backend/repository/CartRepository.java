
package com.ecommerce.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUser_UserId(Integer userId);
}
