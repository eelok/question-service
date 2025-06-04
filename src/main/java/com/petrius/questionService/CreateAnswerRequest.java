package com.petrius.questionService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAnswerRequest {

    @NotBlank(message = "Answer text is mandatory")
    private String answerText;
    @NotNull(message = "The correct field must be specified")
    private Boolean correct;
}
