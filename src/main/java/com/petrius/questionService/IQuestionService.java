package com.petrius.questionService;

import java.util.List;

public interface IQuestionService {

    Question createQuestion(Question question);

    List<QuestionResponse> getAll();

    QuestionResponse getById(long id);

    Question findById(long id);

    Question editQuestion(long id, Question question);

    void deleteQuestion(long id);

    Question findByQuestionText(String name);
}
