package com.acyuta.userservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    @NotBlank(message = "sign.up.first.name.empty")
    private String firstName;

    @NotBlank(message = "sign.up.last.name.empty")
    private String lastName;

    @NotBlank(message = "sign.up.email.empty")
    @Email(message = "sign.up.email.invalid")
    private String email;

    @NotBlank(message = "sign.up.username.empty")
    private String username;

    @NotNull(message = "user.date.of.birth.null")
    private LocalDate dateOfBirth;

    @NotBlank(message = "sign.up.password.empty")
    @Size(min = 5, max = 36)
    private String password;
}
