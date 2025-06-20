package com.petrius.questionService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petrius.questionService.Quiz;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Builder
@ToString(exclude = "answers")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Question text is mandatory")
    private String questionText;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    @NotEmpty(message = "Question must have at least one answer")
    @Size(max = 4, message = "Question can have maximum 4 answers")
    @Valid
    private List<Answer> answers;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnore
    private List<Quiz> quizzes;

}
