package com.rafantasy.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * User Model.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "user.first.name.empty")
    private String firstName;

    @NotBlank(message = "user.last.name.empty")
    private String lastName;

    @NotBlank(message = "user.email.empty")
    @Email(message = "email.invalid")
    private String email;

    @NotBlank(message = "user.username.empty")
    private String username;

    @NotBlank(message = "user.password.empty")
    @JsonIgnore
    private String password;

    @NotNull(message = "user.date.of.birth.null")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    private String token;

    private Boolean isActive = false;

    private Boolean isNonLocked = true;

}
