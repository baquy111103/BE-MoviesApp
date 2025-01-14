package com.example.demo.DTO;
import com.example.demo.Model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private String username;
    private String phone;
    private String email;
    private String language_code;
    private String gender;
    private String bio;
    private String avatar;
    private Date birthday;
    private Date created_at;
    private Date updated_at;
}

