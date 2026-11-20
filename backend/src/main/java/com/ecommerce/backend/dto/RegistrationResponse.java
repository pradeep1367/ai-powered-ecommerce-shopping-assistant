
package com.ecommerce.backend.dto;

import java.time.LocalDateTime;

public class RegistrationResponse {

    private Integer userId;
    private String fullName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    public RegistrationResponse(
            Integer userId,
            String fullName,
            String email,
            String phone,
            LocalDateTime createdAt) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
