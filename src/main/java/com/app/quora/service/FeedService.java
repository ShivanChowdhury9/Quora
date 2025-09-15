package com.app.quora.service;

import com.app.quora.dto.FeedRedisObjectDTO;
import com.app.quora.dto.FeedResponseDTO;
import com.app.quora.enums.UserRole;
import com.app.quora.repository.AnswerRepository;
import com.app.quora.repository.QuestionRepository;
import com.app.quora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FeedService implements IFeedService{

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ReactiveRedisTemplate<String, FeedRedisObjectDTO> redisTemplate;

    @Override
    public Flux<?> getUserFeed(String userId) {
        return userRepository.findById(userId)
                .flatMapMany(user -> {
                    if (user.getFollowingIds() == null || user.getFollowingIds().isEmpty()) {
                        return userRepository.findByRole(UserRole.ADMIN)
                                .flatMapMany(adminUser ->
                                        answerRepository.findByUserId(adminUser.getId())
                                                .flatMap(answer ->
                                                        questionRepository.findById(answer.getQuestionId())
                                                                .map(question -> new FeedResponseDTO(
                                                                        userId,
                                                                        question.getDescription(),
                                                                        answer.getContent()
                                                                ))
                                                )
                                );
                    } else {
                        // TODO: Handle case when user follows others
                        String key = "feed:" + userId;
                        return redisTemplate.opsForZSet()
                                .reverseRange(key, Range.closed(0L, 5L));
                    }
                });

    }
}
