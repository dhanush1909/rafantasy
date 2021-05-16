package com.rafantasy.userservice.security.service;

import com.rafantasy.userservice.common.UserServiceProperties;
import com.rafantasy.userservice.common.Utils;
import com.rafantasy.userservice.role.converter.RoleConverter;
import com.rafantasy.userservice.role.RoleType;
import com.rafantasy.userservice.user.model.User;
import com.rafantasy.userservice.user.repository.UserRepository;
import com.rafantasy.userservice.security.JwtUtils;
import com.rafantasy.userservice.security.UserDetailsImpl;
import com.rafantasy.userservice.security.dto.*;
import com.rafantasy.userservice.security.enums.TokenType;
import com.rafantasy.userservice.security.model.RefreshToken;
import com.rafantasy.userservice.common.Constants;
import com.rafantasy.userservice.security.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * AuthService Implementation.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleConverter roleConverter;

    private final Utils utils;

    private final UserServiceProperties userServiceProperties;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var authorityString = jwtUtils.authorityToString(userDetails.getAuthorities());

        var jwt = jwtUtils.generateJwt(userDetails.getUsername(), authorityString);

        RefreshToken refreshToken = this.createRefreshToken(userDetails.getUsername());

        return new LoginResponseDTO()
                .setJwt(jwt)
                .setExpiryTime(userServiceProperties.getJwtExpiryTimeInMs())
                .setRoles(authorityString)
                .setRefreshToken(refreshToken.getToken())
                .setUsername(userDetails.getUsername());
    }

    @Override
    public ResponseEntity<String> signUp(SignUpRequestDTO signUpRequestDTO) {
        if (!userRepository.existsByEmailOrUsername(signUpRequestDTO.getEmail(), signUpRequestDTO.getUsername())) {
            String token = UUID.randomUUID().toString();
            var user = new User()
                    .setEmail(signUpRequestDTO.getEmail())
                    .setFirstName(signUpRequestDTO.getFirstName())
                    .setLastName(signUpRequestDTO.getLastName())
                    .setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                    .setUsername(signUpRequestDTO.getUsername())
                    .setDateOfBirth(signUpRequestDTO.getDateOfBirth())
                    .setToken(token)
                    .setRoles(Set.of(roleConverter.convert(RoleType.ROLE_USER)));
            userRepository.save(user);

            utils.sendMail(
                    user.getEmail(),
                    Constants.SIGNUP_SUBJECT,
                    String.format(Constants.SIGNUP_PASSWORD_CONTENT,
                            user.getFirstName(),
                            userServiceProperties.getFrontEndUrl(),
                            token));
            return ResponseEntity.ok(String.format("New User %s created", signUpRequestDTO.getUsername()));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.USER_ALREADY_PRESENT);
        }
    }

    @Override
    public Boolean exists(String inputValue) {
        return userRepository.existsByEmailOrUsername(inputValue, inputValue);
    }

    @Override
    public ResponseEntity<String> forgotPasswordRequest(ForgotPasswordDTO forgotPasswordDTO) {
        if (forgotPasswordDTO.getEmail() == null && forgotPasswordDTO.getUsername() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.USERNAME_OR_EMAIL_REQUIRED);

        var token = UUID.randomUUID().toString();
        var user = userRepository.findByUsernameOrEmail(forgotPasswordDTO.getUsername(), forgotPasswordDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.USER_NOT_FOUND));

        utils.sendMail(
                user.getEmail(),
                Constants.RESET_PASSWORD_SUBJECT,
                String.format(Constants.RESET_PASSWORD_CONTENT, userServiceProperties.getFrontEndUrl(), token));
        userRepository.save(user.setToken(token));
        return ResponseEntity.ok("password link sent.");
    }

    @Override
    public ConfirmTokenDTO confirmToken(String resetToken, TokenType tokenType) {
        var user = userRepository.findByToken(resetToken);
        if (user.isEmpty())
            return new ConfirmTokenDTO().setAuthenticated(false);
        var userObject = user.get();
        var confirmToken = new ConfirmTokenDTO()
                .setAuthenticated(true)
                .setUsername(userObject.getUsername());
        if (tokenType.equals(TokenType.signup)) {
            if (!userObject.getIsActive())
                userRepository.save(userObject.setIsActive(true).setToken(null));
            return confirmToken.setTokenType(TokenType.signup);
        }
        return confirmToken.setTokenType(TokenType.forgot_password);
    }

    @Override
    public ResponseEntity<String> resetPassword(UpdatePasswordDTO updatePasswordDTO) {
        var user = userRepository.findByUsernameAndToken(updatePasswordDTO.getUsername(), updatePasswordDTO.getToken())
                .orElseThrow(() -> new BadCredentialsException(Constants.USER_NOT_FOUND));
        userRepository.save(user.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword())).setToken(null));
        return ResponseEntity.ok("updated password successfully.");
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Refresh Token."));
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken =
                new RefreshToken()
                .setToken(UUID.randomUUID().toString())
                .setUser(userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")))
                .setExpiresIn(Instant.now().plusMillis(userServiceProperties.getRefreshTokenExpiryTimeInMs()));

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiresIn().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Override
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String requestRefreshToken = refreshTokenRequestDTO.getRefreshToken();

        RefreshToken refreshToken = this.verifyExpiration(this.findByToken(requestRefreshToken));
        User user = refreshToken.getUser();
        var authorities = new ArrayList<GrantedAuthority>();
        user
                .getRoles()
                .forEach(grantedAuthority -> authorities.add(new SimpleGrantedAuthority(grantedAuthority.getRoleType().toString())));


        String jwt = jwtUtils.generateJwt(refreshToken.getUser().getUsername(), jwtUtils.authorityToString(authorities));

        return new RefreshTokenResponseDto()
                .setRefreshToken(refreshToken.getToken())
                .setToken(jwt)
                .setTokenType("Bearer");
    }

    @Transactional
    public void deleteByUserId(Integer userId) {
        refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
