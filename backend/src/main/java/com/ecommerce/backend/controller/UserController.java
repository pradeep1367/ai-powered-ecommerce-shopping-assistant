
package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.RegistrationRequest;
import com.ecommerce.backend.dto.RegistrationResponse;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new customer
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(
            @Valid @RequestBody RegistrationRequest request) {

        User savedUser = userService.registerUser(request);

        RegistrationResponse response = new RegistrationResponse(
                savedUser.getUserId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getCreatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
