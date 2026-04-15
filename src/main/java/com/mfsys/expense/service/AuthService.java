package com.mfsys.expense.service;


import com.mfsys.expense.dto.LoginRequest;
import com.mfsys.expense.dto.LoginResponse;
import com.mfsys.expense.dto.SignUpRequest;
import com.mfsys.expense.model.User;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    User signUp(SignUpRequest request);

    LoginResponse refreshToken(String refreshToken);
}
