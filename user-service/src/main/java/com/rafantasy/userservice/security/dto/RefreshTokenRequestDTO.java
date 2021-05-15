package com.rafantasy.userservice.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenRequestDTO {

    @NotBlank
    private String refreshToken;
}
