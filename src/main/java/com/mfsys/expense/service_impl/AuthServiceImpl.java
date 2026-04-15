package com.mfsys.expense.service_impl;
import com.mfsys.expense.config.JwtUtil;
import com.mfsys.expense.dto.LoginRequest;
import com.mfsys.expense.dto.LoginResponse;
import com.mfsys.expense.dto.SignUpRequest;
import com.mfsys.expense.model.User;
import com.mfsys.expense.repository.UserRepository;
import com.mfsys.expense.service.AuthService;
import com.mfsys.expense.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    // ✅ LOGIN (FINAL)
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

        return new LoginResponse(accessToken, refreshToken);
    }

    // ✅ SIGNUP
    @Override
    public User signUp(SignUpRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // ⚠️ hash later
        user.setName(request.getName());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    // ✅ REFRESH TOKEN
    @Override
    public LoginResponse refreshToken(String refreshToken) {

        String email = refreshTokenService.validateRefreshToken(refreshToken);

        if (email == null) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtUtil.generateToken(email);

        return new LoginResponse(newAccessToken, refreshToken);
    }
}
