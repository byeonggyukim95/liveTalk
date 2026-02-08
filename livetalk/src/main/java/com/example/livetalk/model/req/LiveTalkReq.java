package com.example.livetalk.model.req;

import jakarta.validation.constraints.NotBlank;

public record LiveTalkReq(
        @NotBlank(message = "{validation.talk.message}")
        String talkMessage) {
}
