package com.example.livetalk.kafka;

import com.example.livetalk.model.resp.LiveTalkMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final String liveTalkTopic = "live-talk";

    public KafkaConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(groupId = "live-talk-group", topics = liveTalkTopic)
    public void liveTalkTopicConsumer(LiveTalkMessage liveTalkMessage, Acknowledgment ack) {
        simpMessagingTemplate.convertAndSend("/topic/public", liveTalkMessage);
        ack.acknowledge();
    }

}