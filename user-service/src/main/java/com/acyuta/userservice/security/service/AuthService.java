package com.acyuta.userservice.security.service;

import com.acyuta.userservice.security.dto.*;
import com.acyuta.userservice.security.dto.*;
import com.acyuta.userservice.security.enums.TokenType;
import com.acyuta.userservice.security.model.RefreshToken;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    ResponseEntity<String> signUp(SignUpRequestDTO signUpRequestDTO);

    Boolean exists(String inputValue);

    ResponseEntity<String> forgotPasswordRequest(ForgotPasswordDTO forgotPasswordDTO);

    ConfirmTokenDTO confirmToken(String resetToken, TokenType tokenType);

    ResponseEntity<String> resetPassword(UpdatePasswordDTO updatePasswordDTO);

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(String username);

    void deleteByUserId(Integer userId);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
