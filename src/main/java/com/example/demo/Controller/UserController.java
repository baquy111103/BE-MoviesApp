package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Đăng ký
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            String result = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Ideally, Spring Security should handle this internally
        // After authentication, return JWT or other tokens
        return ResponseEntity.ok("Login successful");
    }

    // Đăng xuất
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
