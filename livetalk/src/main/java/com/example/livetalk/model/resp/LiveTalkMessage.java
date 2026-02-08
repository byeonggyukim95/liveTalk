package com.example.livetalk.model.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LiveTalkMessage {

    private String memberId;
    private String talkMessage;
    private LocalDateTime createdAt;

    public LiveTalkMessage(String memberId, String talkMessage) {
        this.memberId = memberId;
        this.talkMessage = talkMessage;
        this.createdAt = LocalDateTime.now();
    }

}
