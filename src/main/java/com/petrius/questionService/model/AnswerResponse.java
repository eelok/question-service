package com.petrius.questionService.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class AnswerResponse {

    private Long id;
    private String answerText;
    private Boolean correct;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateAnswerRequest {

        private long id;
        @NotBlank(message = "Answer text is mandatory")
        private String answerText;
        @NotNull(message = "The correct field must be specified")
        private Boolean correct;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateQuestionRequest {

        @NotBlank(message = "Question text is mandatory")
        private String questionText;

        @NotEmpty(message = "Question must have at least one answer")
        public List<UpdateAnswerRequest> answers;

    }
}
