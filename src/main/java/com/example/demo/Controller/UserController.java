package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/auth/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/info")
    public UserDTO getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;

        // Map thông tin người dùng sang DTO
        UserDTO userInfo = new UserDTO();
        userInfo.setUsername(user.getUsername());
        userInfo.setPhone(user.getPhone());
        userInfo.setEmail(user.getEmail());
        userInfo.setLanguage_code(user.getLanguage_code());
        userInfo.setGender(user.getGender());
        userInfo.setBio(user.getBio());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setBirthday(user.getBirthday());
        userInfo.setCreated_at(user.getCreated_at());
        userInfo.setUpdated_at(user.getUpdated_at());

        return userInfo;
    }

    @PutMapping("/update")
    public UserDTO updateUserInfo(@RequestBody UserDTO userDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = (User) userDetails;

        if (userDTO.getUsername() != null) currentUser.setUsername(userDTO.getUsername());
        if (userDTO.getPhone() != null) currentUser.setPhone(userDTO.getPhone());
        if (userDTO.getEmail() != null) currentUser.setEmail(userDTO.getEmail());
        if (userDTO.getLanguage_code() != null) currentUser.setLanguage_code(userDTO.getLanguage_code());
        if (userDTO.getGender() != null) currentUser.setGender(userDTO.getGender());
        if (userDTO.getBio() != null) currentUser.setBio(userDTO.getBio());
        if (userDTO.getAvatar() != null) currentUser.setAvatar(userDTO.getAvatar());
        if (userDTO.getBirthday() != null) currentUser.setBirthday(userDTO.getBirthday());

        currentUser.setUpdated_at(new Date());

        userRepository.save(currentUser);

        userDTO.setUpdated_at(currentUser.getUpdated_at());

        return userDTO;
    }

}
