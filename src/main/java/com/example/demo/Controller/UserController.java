package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/user")
public class UserController {
    @GetMapping
    public ResponseEntity<String> sayHi() {
        return ResponseEntity.ok("Hi");
    }
}
