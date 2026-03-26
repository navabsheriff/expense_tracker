package com.fullstack.expense_tracker.controller;

import com.fullstack.expense_tracker.dto.AuthResponseDTO;
import com.fullstack.expense_tracker.dto.LoginRequestDTO;
import com.fullstack.expense_tracker.dto.UserRequestDTO;
import com.fullstack.expense_tracker.dto.UserResponseDTO;
import com.fullstack.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO requestDTO) {
        return userService.registerUser(requestDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);

    }
}
