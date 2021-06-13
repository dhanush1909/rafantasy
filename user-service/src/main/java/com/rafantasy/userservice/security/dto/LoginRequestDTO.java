package com.rafantasy.userservice.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "login.request.username.empty")
    private String username;

    @NotBlank(message = "login.request.password.empty")
    private String password;

    private String client_id;

    private String grant_type;
}
