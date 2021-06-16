//package com.rafantasy.userservice.feign;
//
//import com.rafantasy.userservice.security.dto.LoginRequestDTO;
//import com.rafantasy.userservice.security.dto.LoginResponseDTO;
//import feign.form.spring.SpringFormEncoder;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.openfeign.support.SpringEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "keycloak-client", url = "${keycloak.auth-server-url}", configuration = KeyCloakClient.Configuration.class)
//public interface KeyCloakClient {
//
//    @PostMapping(value = "/realms/${keycloak.realm}/protocol/openid-connect/token",
//            consumes = "application/x-www-form-urlencoded")
//    LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO);
//
//    class Configuration {
//        @Bean
//        SpringFormEncoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
//            return new SpringFormEncoder(new SpringEncoder(converters));
//        }
//    }
//}