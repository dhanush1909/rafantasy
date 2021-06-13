package com.rafantasy.userservice.security.service;

import com.rafantasy.userservice.security.dto.LoginRequestDTO;
import com.rafantasy.userservice.security.dto.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
