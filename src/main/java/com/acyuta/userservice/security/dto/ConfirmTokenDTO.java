package com.acyuta.userservice.security.dto;

import com.acyuta.userservice.security.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmTokenDTO {

    TokenType tokenType;

    Boolean authenticated;

    String username;

}
