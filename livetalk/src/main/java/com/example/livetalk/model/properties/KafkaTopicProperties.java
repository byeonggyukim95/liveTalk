package com.example.livetalk.model.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.kafka.topic")
@Validated
public record KafkaTopicProperties(
        @NotBlank(message = "토픽 liveTalk 값을 입력해주세요.") String liveTalk) {
}
