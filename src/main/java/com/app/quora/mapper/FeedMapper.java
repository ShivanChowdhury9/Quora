package com.app.quora.mapper;

import com.app.quora.dto.FeedRedisObjectDTO;
import com.app.quora.enums.ParentType;
import com.app.quora.model.Answer;
import com.app.quora.model.Question;

import java.time.Instant;

public class FeedMapper {

    public static FeedRedisObjectDTO createFeedObjectFromAnswer(Answer answer) {
        return FeedRedisObjectDTO.builder()
                .id(answer.getId())
                .userId(answer.getUserId())
                .type(ParentType.ANSWER)
                .content(answer.getContent())
                .build();
    }

    public static FeedRedisObjectDTO createFeedObjectFromQuestion(Question question) {
        return FeedRedisObjectDTO.builder()
                .id(question.getId())
                .userId(question.getUserId())
                .type(ParentType.QUESTION)
                .content(question.getDescription())
                .build();
    }
}
