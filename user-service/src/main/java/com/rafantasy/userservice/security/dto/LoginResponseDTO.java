package com.rafantasy.userservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String username;

//    private Integer id;

    private String jwt;

    private String roles;

    private Long expiryTime;

    private String refreshToken;

}
