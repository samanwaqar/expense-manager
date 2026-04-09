package com.mfsys.expense.config;

public class ByPassURL {

    // Public URLs that don't require JWT authentication
    public static final String[] PUBLIC_URLS = {
            "/auth/login",
            "/auth/sign-up",
            "/v3/api-docs/**",    // Optional: if using Swagger
            "/swagger-ui/**"
    };
}
