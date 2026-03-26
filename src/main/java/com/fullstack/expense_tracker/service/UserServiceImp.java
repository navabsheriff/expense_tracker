package com.fullstack.expense_tracker.service;

import com.fullstack.expense_tracker.dto.AuthResponseDTO;
import com.fullstack.expense_tracker.dto.LoginRequestDTO;
import com.fullstack.expense_tracker.dto.UserRequestDTO;
import com.fullstack.expense_tracker.dto.UserResponseDTO;
import com.fullstack.expense_tracker.entity.User;
import com.fullstack.expense_tracker.repository.UserRepository;
import com.fullstack.expense_tracker.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        User user= new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        String encodedPassword=passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(encodedPassword);
        user=userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        return userResponseDTO;
    }

//    @Override
//    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
//        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        boolean passwordMatch = passwordEncoder.matches(
//                loginRequestDTO.getPassword(),
//                user.getPassword()
//        );
//
//        if (!passwordMatch) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        UserResponseDTO response = new UserResponseDTO();
//        response.setId(user.getId());
//        response.setName(user.getName());
//        response.setEmail(user.getEmail());
//
//        return response;
//    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 🔥 Generate token
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDTO(token);
    }
}
