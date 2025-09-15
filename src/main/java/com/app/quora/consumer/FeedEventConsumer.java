package com.app.quora.consumer;

import com.app.quora.dto.FeedRedisObjectDTO;
import com.app.quora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FeedEventConsumer {


    private final ReactiveRedisTemplate<String, FeedRedisObjectDTO> redisTemplate;
    private final UserRepository userRepository;

    @KafkaListener(topics = "Feed-Topic", groupId = "feed-topic-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void consume(FeedRedisObjectDTO feed) {
        try {
            // find followers of the user who posted
            userRepository.findById(feed.getUserId())
                    .flatMapMany(user -> Flux.fromIterable(user.getFollowersIds()))
                    .flatMap(followerId -> {
                        String key = "feed:" + followerId;
                        return redisTemplate.opsForZSet()
                                .add(key, feed, Instant.now().toEpochMilli());
                    })
                    .subscribe();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
