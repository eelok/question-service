package com.petrius.questionService.mapper;
import com.petrius.questionService.model.AnswerResponse;
import com.petrius.questionService.model.CreateAnswerRequest;
import com.petrius.questionService.model.CreateQuestionRequest;
import com.petrius.questionService.model.QuestionResponse;
import com.petrius.questionService.entity.Answer;
import com.petrius.questionService.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    public QuestionResponse toQuestionResponse(Question question){
        List<Answer> answers = question.getAnswers();
        List<AnswerResponse> answersResponse = answers
                .stream()
                .map(answer ->
                    this.answerMapper.toAnswerResponse(answer))
                .collect(Collectors.toList());

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .answers(answersResponse)
                .build();
    }

    public Question toQuestion(CreateQuestionRequest questionRequest){
        List<CreateAnswerRequest> answersRequest = questionRequest.getAnswers();
        List<Answer> answers = answersRequest.stream()
                .map(a -> this.answerMapper.toAnswer(a))
                .toList();

        Question question = Question
                .builder()
                .questionText(questionRequest.getQuestionText())
                .answers(answers)
                .build();
        answers.forEach(a -> a.setQuestion(question));
        return question;
    }
}
