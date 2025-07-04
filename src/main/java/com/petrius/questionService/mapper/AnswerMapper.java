package com.petrius.questionService.mapper;

import com.petrius.questionService.model.AnswerResponse;
import com.petrius.questionService.model.CreateAnswerRequest;
import com.petrius.questionService.entity.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public AnswerResponse toAnswerResponse(Answer answer){
        return AnswerResponse.builder()
                .id(answer.getId())
                .answerText(answer.getAnswerText())
                .correct(answer.getCorrect())
                .build();
    }

    public Answer toAnswer(CreateAnswerRequest answerRequest){
        return Answer.builder()
                .answerText(answerRequest.getAnswerText())
                .correct(answerRequest.getCorrect())
                .build();

    }
}
