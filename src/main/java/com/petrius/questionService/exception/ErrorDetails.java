package com.petrius.questionService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String details;

}
