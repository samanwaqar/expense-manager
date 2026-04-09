package com.mfsys.expense.dto;

import lombok.Data;

@Data
public class SignUpRequest {
        private String email;
        private String name;
        private String password;
        private String role;

}
