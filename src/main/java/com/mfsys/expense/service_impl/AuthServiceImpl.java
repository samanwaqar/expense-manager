package com.mfsys.expense.service_impl;

import com.mfsys.expense.config.JwtUtil;
import com.mfsys.expense.dto.LoginRequest;
import com.mfsys.expense.dto.LoginResponse;
import com.mfsys.expense.dto.SignUpRequest;
import com.mfsys.expense.model.User;
import com.mfsys.expense.repository.UserRepository;
import com.mfsys.expense.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. Fetch user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate password
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3. Generate token based on email only
        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

    @Override
    public User signUp(SignUpRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Use hashing in production!
        user.setName(request.getName());
        user.setRole(request.getRole()); // optional, not used in JWT

        return userRepository.save(user);
    }
}
