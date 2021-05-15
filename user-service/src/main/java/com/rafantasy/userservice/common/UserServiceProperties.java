package com.rafantasy.userservice.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties with prefix 'user-service-properties'.
 */
@ConfigurationProperties(prefix = "user-service-properties")
@Data
@Component
public class UserServiceProperties {

    String jwtSecret;

    Long jwtExpiryTimeInMs;

    String frontEndUrl;

    Long refreshTokenExpiryTimeInMs;
}
