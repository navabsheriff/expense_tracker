package com.fullstack.expense_tracker.service;

import com.fullstack.expense_tracker.dto.AuthResponseDTO;
import com.fullstack.expense_tracker.dto.LoginRequestDTO;
import com.fullstack.expense_tracker.dto.UserRequestDTO;
import com.fullstack.expense_tracker.entity.User;
import com.fullstack.expense_tracker.dto.UserResponseDTO;
public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
//    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    User findByEmail(String email);
    AuthResponseDTO login(LoginRequestDTO request);
}
