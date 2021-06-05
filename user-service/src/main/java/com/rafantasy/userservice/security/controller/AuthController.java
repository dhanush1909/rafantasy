//package com.rafantasy.userservice.security.controller;
//
//import com.rafantasy.userservice.security.dto.*;
//import com.rafantasy.userservice.security.enums.TokenType;
//import com.rafantasy.userservice.security.service.AuthService;
//import com.rafantasy.userservice.security.dto.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    /**
//     * Method to authenticate a user.
//     *
//     * @param loginRequestDTO The login request dto.
//     * @return The login response, on successful authentication.
//     */
//    @PostMapping("/login")
//    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
//        return authService.login(loginRequestDTO);
//    }
//
//    /**
//     * Method to register -- a new user.
//     *
//     * @param signUpRequestDTO Signup dto with all of the required information.
//     * @return Response on successful login.
//     */
//    @PostMapping("/signup")
//    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
//        return authService.signUp(signUpRequestDTO);
//    }
//
//    /**
//     * Method to check if a user already exists by username or email.
//     *
//     * @param inputValue The input-value, can be an email or a username.
//     * @return True if exists, False otherwise.
//     */
//    @GetMapping("/exists-by-username-or-email/{input-value}")
//    public Boolean existsByUsername(@PathVariable("input-value") String inputValue) {
//        return authService.exists(inputValue);
//    }
//
//    /**
//     * Method to request for password change.
//     *
//     * @param forgotPasswordDTO The forgot password dto.
//     * @return ResponseEntity object.
//     */
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> passwordResetRequest(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
//        return authService.forgotPasswordRequest(forgotPasswordDTO);
//    }
//
//    /**
//     * Method to confirm the authenticity of a token, used either for signup or reset password.
//     *
//     * @param token     The token.
//     * @param tokenType The tokentype - either forgotPassword or signup.
//     * @return ConfirmTokenDto on successful validation.
//     */
//    @GetMapping("/confirm-token/{token-type}/{token}")
//    public ConfirmTokenDTO confirmToken(@PathVariable("token") String token, @PathVariable("token-type") TokenType tokenType) {
//        return authService.confirmToken(token, tokenType);
//    }
//
//    /**
//     * Method to reset the password.
//     *
//     * @param updatePasswordDTO DTO with appropriate fields to required to reset the password.
//     * @return ResponseEntity on successful password change.
//     */
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> confirmResetPassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
//        return authService.resetPassword(updatePasswordDTO);
//    }
//
//    /**
//     * Method to refresh a JWT token.
//     * @param refreshTokenRequestDTO DTO with Refresh token string.
//     * @return New JWT Bearer Token.
//     */
//    @PostMapping("/refresh-token")
//    public RefreshTokenResponseDto refreshToken(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) {
//        return authService.refreshToken(refreshTokenRequestDTO);
//    }
//
//}
