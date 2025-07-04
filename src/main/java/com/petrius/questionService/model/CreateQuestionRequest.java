package com.petrius.questionService.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionRequest {

    @NotBlank(message = "Question text is mandatory")
    private String questionText;

    @NotEmpty(message = "Question must have at least one answer")
    public List<CreateAnswerRequest> answers;

}
