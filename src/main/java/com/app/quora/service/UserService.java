package com.app.quora.service;


import com.app.quora.dto.FeedRedisObjectDTO;
import com.app.quora.dto.UserRequestDTO;
import com.app.quora.dto.UserResponseDTO;
import com.app.quora.enums.ParentType;
import com.app.quora.mapper.UserMapper;
import com.app.quora.model.Answer;
import com.app.quora.model.Question;
import com.app.quora.repository.AnswerRepository;
import com.app.quora.repository.QuestionRepository;
import com.app.quora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ReactiveRedisTemplate<String, FeedRedisObjectDTO> redisTemplate;

    @Override
    public Mono<UserResponseDTO> createUser(UserRequestDTO user) {
        return userRepository.save(UserMapper.userRequestDTOToUser(user))
                .map(UserMapper::userToUserResponseDTO);
    }

    @Override
    public Mono<UserResponseDTO> getUserById(String userId) {
        return userRepository.findById(userId).map(UserMapper::userToUserResponseDTO);
    }

    @Override
    public Mono<UserResponseDTO> follow(String userId1, String userId2) {

        return userRepository.findById(userId2)
                .flatMap(user2 -> {
                    user2.getFollowersIds().add(userId1);
                    return userRepository.save(user2);
                })
                .flatMap(user2 -> userRepository.findById(userId1).flatMap(user1 -> {
                    user1.getFollowingIds().add(userId2);
                    return userRepository.save(user1);
                }))
                .flatMap(user1 -> {
                    // Fetch old questions & answers by user2
                    Flux<Question> questions = questionRepository.findByUserId(userId2);
                    Flux<Answer> answers = answerRepository.findByUserId(userId2);

                    // Merge and map into FeedRedisObjectDTO
                    Flux<FeedRedisObjectDTO> feedFlux = Flux.merge(questions, answers)
                            .map(item -> FeedRedisObjectDTO.builder()
                                    .userId(userId2)
                                    .content(item instanceof Question
                                            ? ((Question) item).getTitle()
                                            : ((Answer) item).getContent())
                                    .type(item instanceof Question ? ParentType.QUESTION : ParentType.ANSWER)
                                    .id(item instanceof Question
                                            ? ((Question) item).getId()
                                            : ((Answer) item).getId())
                                    .build());

                    // Push into Redis sorted set for user1’s feed
                    return feedFlux.flatMap(feed ->
                                    redisTemplate.opsForZSet()
                                            .add("feed:" + userId1, feed, Instant.now().toEpochMilli())
                            )
                            .then(Mono.just(UserMapper.userToUserResponseDTO(user1)));
                });
    }
}
