//package com.rafantasy.userservice.security;
//
//import com.rafantasy.userservice.domain.service.UserRepository;
//import com.rafantasy.userservice.common.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new UserDetailsImpl(userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException(Constants.USER_NOT_FOUND)));
//    }
//}
