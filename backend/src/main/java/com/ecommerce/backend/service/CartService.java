
package com.ecommerce.backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Add product to cart
    @Transactional
    public CartItem addProductToCart(
            String email,
            Integer productId,
            Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found"));

        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException(
                    "Requested quantity exceeds available stock");
        }

        Cart cart = cartRepository.findByUser_UserId(user.getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = cartItemRepository
                .findByCart_CartIdAndProduct_ProductId(
                        cart.getCartId(),
                        productId)
                .orElse(null);

        if (cartItem != null) {

            int updatedQuantity =
                    cartItem.getQuantity() + quantity;

            if (updatedQuantity > product.getStockQuantity()) {
                throw new IllegalArgumentException(
                        "Total quantity exceeds available stock");
            }

            cartItem.setQuantity(updatedQuantity);

        } else {

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }

        return cartItemRepository.save(cartItem);
    }

    // View all products in cart
    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found"));

        Cart cart = cartRepository.findByUser_UserId(user.getUserId())
                .orElse(null);

        if (cart == null) {
            return Collections.emptyList();
        }

        return cartItemRepository.findByCart_CartId(
                cart.getCartId());
    }

    // Update product quantity
    @Transactional
    public CartItem updateCartItemQuantity(
            String email,
            Integer cartItemId,
            Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found"));

        Cart cart = cartRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cart not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cart item not found"));

        if (!cartItem.getCart().getCartId().equals(cart.getCartId())) {
            throw new IllegalArgumentException(
                    "Cart item does not belong to this user");
        }

        Product product = cartItem.getProduct();

        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException(
                    "Requested quantity exceeds available stock");
        }

        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    // Remove product from cart
    @Transactional
    public void removeCartItem(
            String email,
            Integer cartItemId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found"));

        Cart cart = cartRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cart not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cart item not found"));

        if (!cartItem.getCart().getCartId().equals(cart.getCartId())) {
            throw new IllegalArgumentException(
                    "Cart item does not belong to this user");
        }

        cartItemRepository.delete(cartItem);
    }
}
