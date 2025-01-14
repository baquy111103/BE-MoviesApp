package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String language_code;
    private String gender;
    private String bio;
    private String avatar;
    private Date birthday;
}
