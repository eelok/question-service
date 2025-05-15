package com.petrius.questionService;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor
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
