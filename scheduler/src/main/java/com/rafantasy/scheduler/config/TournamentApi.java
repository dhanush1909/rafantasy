package com.rafantasy.scheduler.config;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "livescore-api")
public class TournamentApi {

    String baseUrl;
    String category;
    String xRapidapiKey;
}
