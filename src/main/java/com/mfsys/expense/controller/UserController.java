package com.mfsys.expense.controller;

import com.mfsys.expense.dto.ProfileResponse;
import com.mfsys.expense.service.UserService;
import org.springframework.web.bind.annotation.*;

import static com.mfsys.expense.config.UrlConstants.*;

@RestController
@RequestMapping(USER) // e.g. "/user"
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET PROFILE
    @GetMapping(PROFILE)
    public ProfileResponse getProfile() {
        return service.getProfile();
    }

    // UPDATE PROFILE
    @PutMapping(UPDATE_PROFILE)
    public ProfileResponse updateProfile(@RequestBody ProfileResponse request) {
        return service.updateProfile(request);
    }
}
