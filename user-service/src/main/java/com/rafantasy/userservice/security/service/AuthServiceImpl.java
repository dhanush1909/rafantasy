package com.rafantasy.userservice.security.service;

import com.rafantasy.userservice.feign.KeyCloakClient;
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

    private final KeyCloakClient keyCloakClient;

    @Value("${keycloak.resource}")
    private String clientId;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        // Setting clientId and grantType
        loginRequestDTO.setClient_id(clientId);
        loginRequestDTO.setGrant_type("password");

        return keyCloakClient.login(loginRequestDTO);
    }
}
