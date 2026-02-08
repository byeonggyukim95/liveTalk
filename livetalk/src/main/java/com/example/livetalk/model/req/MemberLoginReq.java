package com.example.livetalk.model.req;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginReq(
        @NotBlank(message = "{validation.member.id}")
        String memberId,
        @NotBlank(message = "{validation.member.password}")
        String password) {
}
