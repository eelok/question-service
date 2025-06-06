package com.petrius.questionService.service;

import com.petrius.questionService.entity.Question;
import com.petrius.questionService.model.AnswerResponse;
import com.petrius.questionService.model.CreateQuestionRequest;
import com.petrius.questionService.model.QuestionResponse;

import java.util.List;

public interface IQuestionService {

    QuestionResponse createQuestion(CreateQuestionRequest questionRequest);

    List<QuestionResponse> getAll();

    QuestionResponse getById(long id);

    QuestionResponse editQuestion(long id, AnswerResponse.UpdateQuestionRequest question);

    void deleteQuestion(long id);

    Question findByQuestionText(String name);
}
