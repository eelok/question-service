package com.petrius.questionService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class AnswerResponse {

    private Long id;
    private String answerText;
    private Boolean correct;

}
