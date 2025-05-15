package com.petrius.questionService;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
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
    @Valid
    private List<Answer> answers;

}
