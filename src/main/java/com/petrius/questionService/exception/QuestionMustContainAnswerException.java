package com.petrius.questionService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionMustContainAnswerException extends RuntimeException{

    public QuestionMustContainAnswerException(String message) {
        super(message);
    }
}
