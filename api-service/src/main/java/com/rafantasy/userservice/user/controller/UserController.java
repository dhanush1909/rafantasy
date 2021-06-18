package com.rafantasy.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import static com.rafantasy.shared.common.Constants.POC_APP_NO_REPLY; // example

/**
 * User Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @GetMapping
    public String getAllUsers() {
        return "works";
    }


}
