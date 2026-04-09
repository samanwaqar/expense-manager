package com.mfsys.expense.controller;

import com.mfsys.expense.dto.LoginRequest;
import com.mfsys.expense.dto.LoginResponse;
import com.mfsys.expense.dto.SignUpRequest;
import com.mfsys.expense.model.User;
import com.mfsys.expense.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }
}
