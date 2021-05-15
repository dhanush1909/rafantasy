package com.acyuta.userservice.common;

/**
 * This class contains all of the constants.
 */
public class Constants {
    public static final String POC_APP_NO_REPLY = "noreply@pocApp.com";
    public static final String ROLE_NOT_FOUND = "role.not.found";
    public static final String USER_NOT_FOUND = "user.not.found";
    public static final String WRONG_PASSWORD_PROVIDED = "wrong.password.provided";
    public static final String USERNAME_OR_EMAIL_REQUIRED = "username.or.password.required";
    public static final String JWT_ISSUER = "vuejsPoc";
    public static final String JWT_SUBJECT = "JWT-string-for-authentication";
    public static final String JWT_USERNAME_CLAIM = "username";
    public static final String JWT_AUTHORITIES_CLAIM = "authorities";
    public static final String JWT_BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String COMMA_DELIM = ",";
    public static final String RESET_PASSWORD_SUBJECT = "Your password";
    public static final String SIGNUP_SUBJECT = "Confirm your E-mail";
    public static final String RESET_PASSWORD_CONTENT = "Click on this link to change your password: %s/confirm-token/forgot_password/%s";
    public static final String SIGNUP_PASSWORD_CONTENT = "Dear %s,\n Please click on this link to confirm your email %s/confirm-token/signup/%s";
    public static final String USER_ALREADY_PRESENT = "username.or.email.already.exist";
}
