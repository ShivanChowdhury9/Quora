package com.app.quora.producer;

import com.app.quora.configuration.KafkaConfig;
import com.app.quora.dto.FeedRedisObjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishFeedEvent(FeedRedisObjectDTO feedItem) {
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, feedItem.getUserId(), feedItem);
    }
}
