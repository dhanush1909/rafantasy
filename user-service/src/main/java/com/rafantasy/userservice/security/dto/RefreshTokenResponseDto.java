package com.rafantasy.userservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponseDto {
    private String token;
    private String refreshToken;
    private String tokenType = "Bearer";
}
