package com.petrius.questionService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionExistsException extends RuntimeException{

    public QuestionExistsException(String message){
        super(message);
    }
}
