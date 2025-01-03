package com.example.demo.DTO;
import com.example.demo.Model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created_at;
    private Date updated_at;
    private Boolean active;
    private String language_code;
    private String gender;
    private String bio;
    private String avatar;
    private Date birthday;
    private Boolean status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserDTO(User user) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
        this.language_code = language_code;
        this.gender = gender;
        this.bio = bio;
        this.avatar = avatar;
        this.birthday = birthday;
        this.status = status;
    }

}

