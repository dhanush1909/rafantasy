package com.rafantasy.userservice.security.service;

import com.rafantasy.userservice.security.dto.*;
import com.rafantasy.userservice.security.enums.TokenType;
import com.rafantasy.userservice.security.model.RefreshToken;
import com.rafantasy.userservice.security.dto.*;
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
