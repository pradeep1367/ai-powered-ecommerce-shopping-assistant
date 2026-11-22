
package com.ecommerce.backend.dto;

public class CartItemResponse {

    private Integer cartItemId;
    private Integer productId;
    private Integer quantity;

    public CartItemResponse(
            Integer cartItemId,
            Integer productId,
            Integer quantity) {

        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
