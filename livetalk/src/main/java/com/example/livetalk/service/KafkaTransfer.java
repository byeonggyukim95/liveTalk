package com.example.livetalk.service;

import com.example.livetalk.model.properties.KafkaTopicProperties;
import com.example.livetalk.model.resp.LiveTalkMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTransfer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public KafkaTransfer(KafkaTemplate<String, Object> kafkaTemplate,
                         KafkaTopicProperties kafkaTopicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicProperties = kafkaTopicProperties;
    }

    public void publishLiveTalkMessage(String memberId, String talkMessage) {
        kafkaTemplate.send(kafkaTopicProperties.liveTalk(), "public-room", new LiveTalkMessage(memberId, talkMessage));
    }

}
