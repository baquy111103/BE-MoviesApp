package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lấy JWT_SECRET từ application.properties
    @Value("${JWT_SECRET}")
    private String jwtSecret;

    // Đăng ký người dùng
    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already taken";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());
        user.setActive(true);
        userRepository.save(user);
        return "User registered successfully";
    }

    // Đăng nhập người dùng và trả về JWT token
    public String loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "Username or password error";
        }

        User user = userOptional.get();

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Email or password error";
        }

        // Tạo khóa bí mật với kích thước đúng 512 bits
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Tạo JWT Token nếu đăng nhập thành công
        String token = Jwts.builder()
                .setSubject(user.getEmail())  // Lấy email làm subject
                .setIssuedAt(new Date())      // Thời gian tạo token
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // Token hết hạn trong 1 ngày
                .signWith(key)  // Sử dụng khóa bí mật tạo từ secretKeyFor và thuật toán HS512
                .compact();

        return token; // Trả về token JWT
    }
}
