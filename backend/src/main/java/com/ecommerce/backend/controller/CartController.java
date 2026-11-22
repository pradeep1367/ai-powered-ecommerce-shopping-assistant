
package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.dto.CartItemResponse;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.service.CartService;

@RestController
@RequestMapping("/api/v1/customer/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add product to cart
    @PostMapping("/items")
    public ResponseEntity<CartItemResponse> addProductToCart(
            Authentication authentication,
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {

        String email = authentication.getName();

        CartItem cartItem = cartService.addProductToCart(
                email, productId, quantity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(cartItem));
    }

    // View cart
    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCart(
            Authentication authentication) {

        String email = authentication.getName();

        List<CartItemResponse> response = cartService
                .getCartItems(email)
                .stream()
                .map(this::convertToResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    // Update cart item quantity
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItemResponse> updateQuantity(
            Authentication authentication,
            @PathVariable Integer cartItemId,
            @RequestParam Integer quantity) {

        String email = authentication.getName();

        CartItem cartItem = cartService.updateCartItemQuantity(
                email, cartItemId, quantity);

        return ResponseEntity.ok(convertToResponse(cartItem));
    }

    // Remove product from cart
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<String> removeCartItem(
            Authentication authentication,
            @PathVariable Integer cartItemId) {

        String email = authentication.getName();

        cartService.removeCartItem(email, cartItemId);

        return ResponseEntity.ok(
                "Product removed from cart successfully!");
    }

    // Convert entity into response DTO
    private CartItemResponse convertToResponse(CartItem cartItem) {

        return new CartItemResponse(
                cartItem.getCartItemId(),
                cartItem.getProduct().getProductId(),
                cartItem.getQuantity()
        );
    }
}
