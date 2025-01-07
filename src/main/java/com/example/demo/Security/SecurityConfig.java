package com.example.demo.Security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Manager Bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Tắt CSRF cho các API hoặc dịch vụ stateless

                // Cấu hình các đường dẫn và quyền truy cập
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/logout").permitAll() // Cho phép truy cập các API này mà không cần xác thực
                                .anyRequest().authenticated()  // Các request còn lại cần phải xác thực
                )

                .formLogin(formLogin -> formLogin
                        .disable() )

                // Cấu hình logout
                .logout(logout ->
                        logout
                                .logoutUrl("/api/auth/logout")  // Đường dẫn logout
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    response.setStatus(HttpServletResponse.SC_OK); // Trả về mã 200 OK
                                    response.getWriter().write("Logout successful");  // Trả về thông báo logout thành công dưới dạng JSON
                                    response.getWriter().flush();
                                })     // URL sau khi logout thành công
                                .invalidateHttpSession(true)    // Hủy session sau khi logout
                                .clearAuthentication(true)      // Xóa thông tin xác thực
                                .permitAll()                    // Cho phép tất cả người dùng logout
                );

        return http.build();
    }

}
