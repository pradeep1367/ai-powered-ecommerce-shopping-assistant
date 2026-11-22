
package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorizationTestController {

    // CUSTOMER-only test endpoint
    @GetMapping("/customer/test")
    public String customerTest() {
        return "Customer access granted successfully!";
    }

    // ADMIN-only test endpoint
    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin access granted successfully!";
    }
}
