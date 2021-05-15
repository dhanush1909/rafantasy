package com.acyuta.userservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.acyuta.userservice.common.Constants.*;

@Slf4j
@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        var jwt = jwtUtils.parseJwt(httpServletRequest.getHeader(AUTHORIZATION_HEADER));

        try {
            if (jwt != null) {
                var claims = jwtUtils.parseClaims(jwt);

                var username = String.valueOf(claims.get(JWT_USERNAME_CLAIM));
                var userDetails = userDetailsService.loadUserByUsername(username);
                var authorities = jwtUtils.authorityStringToArray(String.valueOf(claims.get(JWT_AUTHORITIES_CLAIM)));

                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Error while parsing jwt: {}", ex.getMessage());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
