package com.petrius.questionService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;
    private String questionText;
    private List<AnswerResponse> answers;
}
