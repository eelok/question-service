package com.petrius.questionService.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionExistException extends RuntimeException{

    public QuestionExistException(String message){
        super(message);
    }
}
