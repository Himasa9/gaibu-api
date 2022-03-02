package com.example.gaibuapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApplicationProperties {
    @Value("${twitter.auth.api-key}")
    private String API_KEY;
    @Value("${twitter.auth.api-key-secret}")
    private String API_KEY_SECRET;
    @Value("${twitter.auth.access-token}")
    private String ACCESS_TOKEN;
    @Value("${twitter.auth.access-token-secret}")
    private String ACCESS_TOKEN_SECRET;
}