package com.rafantasy.userservice.security.controller;

import com.rafantasy.userservice.security.dto.LoginRequestDTO;
import com.rafantasy.userservice.security.dto.LoginResponseDTO;
import com.rafantasy.userservice.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Method to authenticate a user.
     *
     * @param loginRequestDTO The login request dto.
     * @return The login response, on successful authentication.
     */
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }

//    /** //todo
//     * Method to register -- a new user.
//     *
//     * @param signUpRequestDTO Signup dto with all of the required information.
//     * @return Response on successful login.
//     */
//    @PostMapping("/signup")
//    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
//        return authService.signUp(signUpRequestDTO);
//    }
}
