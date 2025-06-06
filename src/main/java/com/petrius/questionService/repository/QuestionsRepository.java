package com.petrius.questionService.repository;

import com.petrius.questionService.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

    Question findByQuestionText(String name);
}
