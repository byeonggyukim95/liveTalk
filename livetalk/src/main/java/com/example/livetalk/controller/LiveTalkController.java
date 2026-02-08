package com.example.livetalk.controller;

import com.example.livetalk.model.req.LiveTalkReq;
import com.example.livetalk.service.KafkaTransfer;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LiveTalkController {

    private final KafkaTransfer kafkaTransfer;

    public LiveTalkController(KafkaTransfer kafkaTransfer) {
        this.kafkaTransfer = kafkaTransfer;
    }

    @MessageMapping("/talk")
    public void sendMessage(@Valid LiveTalkReq liveTalkReq, Principal principal) throws Exception {
        kafkaTransfer.publishLiveTalkMessage(principal.getName(), liveTalkReq.talkMessage());
    }

}