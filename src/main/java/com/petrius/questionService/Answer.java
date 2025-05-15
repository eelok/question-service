package com.petrius.questionService;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String answerText;

    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;

}
