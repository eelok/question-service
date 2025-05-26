package com.petrius.questionService;

import java.util.List;

public interface IQuestionService {

    Question createQuestion(Question question);

    List<Question> findAll();

    Question findById(long id);

    Question editQuestion(long id, Question question);

    void deleteQuestion(long id);
}
