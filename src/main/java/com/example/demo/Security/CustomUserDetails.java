package com.example.demo.Security;

import com.example.demo.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về quyền của người dùng, ví dụ: ROLE_USER hoặc ROLE_ADMIN
        return List.of(new SimpleGrantedAuthority("ROLE_" + (user.getStatus() ? "ADMIN" : "USER")));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Trả về email thay vì username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getActive(); // Kiểm tra trạng thái hoạt động của tài khoản
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive(); // Kiểm tra trạng thái hoạt động của tài khoản
    }

    public User getUser() {
        return user;
    }
}
