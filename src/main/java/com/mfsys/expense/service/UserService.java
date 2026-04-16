package com.mfsys.expense.service;

import com.mfsys.expense.model.User;
import com.mfsys.expense.repository.UserRepository;
import com.mfsys.expense.dto.ProfileResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET PROFILE
    public ProfileResponse getProfile() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // UPDATE PROFILE
    public ProfileResponse updateProfile(ProfileResponse request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());

        userRepository.save(user);

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
