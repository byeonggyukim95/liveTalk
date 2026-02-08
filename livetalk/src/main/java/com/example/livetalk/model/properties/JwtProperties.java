package com.example.livetalk.model.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "jwt")
@Validated
public record JwtProperties(
        @NotBlank(message = "jwt issuer 설정이 필요합니다.") String issuer,
        @NotBlank(message = "jwt secret 설정이 필요합니다.") String secret,
        @NotNull(message = "jwt tokenExpiration 설정이 필요합니다.") Long tokenExpiration
) {
}
