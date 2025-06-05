package com.petrius.questionService;

import java.util.List;

public interface IQuestionService {

    QuestionResponse createQuestion(CreateQuestionRequest questionRequest);

    List<QuestionResponse> getAll();

    QuestionResponse getById(long id);

    QuestionResponse editQuestion(long id, UpdateQuestionRequest question);

    void deleteQuestion(long id);

    Question findByQuestionText(String name);
}
