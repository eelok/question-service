package com.petrius.questionService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@ToString(exclude = "question")
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Answer text is mandatory")
    private String answerText;

    @NotNull(message = "The correct field must be specified")
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;

}
