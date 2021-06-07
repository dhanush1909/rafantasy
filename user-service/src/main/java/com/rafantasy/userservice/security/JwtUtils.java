//package com.rafantasy.userservice.security;
//
//import com.rafantasy.userservice.common.UserServiceProperties;
//import com.rafantasy.userservice.common.Constants;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.stream.Collectors;
//
///**
// * Jwt specific utility class.
// */
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtUtils {
//
//    private final UserServiceProperties userServiceProperties;
//
//    /**
//     * Method to generate new JWT.
//     *
//     * @param username    The username.
//     * @param authorities The authorities.
//     * @return JWT String.
//     */
//    public String generateJwt(String username, String authorities) {
//        var secret = Keys.hmacShaKeyFor(userServiceProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8));
//        return Jwts.builder()
//                .signWith(secret)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + userServiceProperties.getJwtExpiryTimeInMs()))
//                .setSubject(Constants.JWT_SUBJECT)
//                .setIssuer(Constants.JWT_ISSUER)
//                .claim(Constants.JWT_USERNAME_CLAIM, username)
//                .claim(Constants.JWT_AUTHORITIES_CLAIM, authorities)
//                .compact();
//    }
//
//    /**
//     * Convert authority list to authority String.
//     *
//     * @param authorities List of authorities.
//     * @return String of authorities separated by comma.
//     */
//    public String authorityToString(Collection<? extends GrantedAuthority> authorities) {
//        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(Constants.COMMA_DELIM));
//    }
//
//    /**
//     * To parse a jwt raw string, comprising of "Bearer jwt...".
//     *
//     * @param jwt The jwt.
//     * @return Parsed jwt.
//     */
//    public String parseJwt(String jwt) {
//        if (StringUtils.hasText(jwt) && jwt.startsWith(Constants.JWT_BEARER))
//            return jwt.substring(7);
//        return null;
//    }
//
//    /**
//     * Fetching claims/payload from jwt.
//     *
//     * @param jwt The jwt string.
//     * @return The Claims.
//     */
//    public Claims parseClaims(String jwt) {
//        var secret = Keys.hmacShaKeyFor(userServiceProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8));
//        return Jwts.parserBuilder()
//                .setSigningKey(secret)
//                .build()
//                .parseClaimsJws(jwt)
//                .getBody();
//    }
//
//    /**
//     * To convert authority string separated by "," to Array.
//     *
//     * @param authorities String of authorities.
//     * @return List of authorities.
//     */
//    public Collection<? extends GrantedAuthority> authorityStringToArray(String authorities) {
//        var authoritiesArray = new ArrayList<GrantedAuthority>();
//        Arrays.asList(authorities.split(Constants.COMMA_DELIM))
//                .forEach(i -> authoritiesArray.add(new SimpleGrantedAuthority(i)));
//        return authoritiesArray;
//    }
//}
