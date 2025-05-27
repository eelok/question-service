package com.petrius.questionService.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionExistsException extends RuntimeException{

    public QuestionExistsException(String message){
        super(message);
    }
}
