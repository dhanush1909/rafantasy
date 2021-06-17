//package com.rafantasy.userservice.domain.converter;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//import static com.rafantasy.userservice.common.Constants.USER_NOT_FOUND;
//
//@Component
//@RequiredArgsConstructor
//public class UserConverter implements Converter<String, User> {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public User convert(String userName) {
//        return userRepository.findByUsername(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_FOUND));
//    }
//}