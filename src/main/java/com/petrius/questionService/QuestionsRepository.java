package com.petrius.questionService;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

    Question findByQuestionText(String name);
}
