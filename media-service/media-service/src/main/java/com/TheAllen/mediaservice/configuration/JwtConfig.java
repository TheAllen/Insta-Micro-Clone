package com.TheAllen.mediaservice.configuration;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class JwtConfig {

    @Value()
    private String uri;

    @Value()
    private String header;

    @Value()
    private String prefix;

    @Value()
    private int expiration;

    @Value()
    private String secret;
}
