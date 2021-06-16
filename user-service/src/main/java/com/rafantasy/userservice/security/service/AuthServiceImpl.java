package com.rafantasy.userservice.security.service;

import com.rafantasy.userservice.security.dto.LoginRequestDTO;
import com.rafantasy.userservice.security.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * AuthService Implementation.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {



    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        return null;
    }
}
