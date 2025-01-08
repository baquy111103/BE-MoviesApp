package com.example.demo.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Lấy JWT từ tiêu đề yêu cầu (Authorization Header)
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Nếu không có Authorization Header hoặc không phải Bearer token, tiếp tục xử lý
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // Bỏ qua "Bearer "
        try {
            // Kiểm tra tính hợp lệ của token
            if (jwtUtils.validateJwtToken(token)) {
                String email = jwtUtils.getEmailFromJwtToken(token);

                // Kiểm tra email được trích xuất có hợp lệ không
                if (email != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Đặt người dùng hiện tại vào context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Authorization Header: " + authHeader); // Để kiểm tra giá trị header
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email không có trong JWT");
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ");
                return;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lỗi xác thực JWT: " + e.getMessage());
            return;
        }

        // Tiếp tục với filter chain
        filterChain.doFilter(request, response);
    }
}
