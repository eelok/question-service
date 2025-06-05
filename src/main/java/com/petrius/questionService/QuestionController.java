package com.petrius.questionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class QuestionController {


    @Autowired
    private IQuestionService iQuestionService;


    @PostMapping("api/v1/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody CreateQuestionRequest questionRequest){
        QuestionResponse createdQuestion = this.iQuestionService.createQuestion(questionRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping("api/v1/questions")
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<QuestionResponse> questions = this.iQuestionService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("api/v1/questions/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable long id){
        QuestionResponse question = this.iQuestionService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @PutMapping("api/v1/questions/{id}")
    public ResponseEntity<QuestionResponse> editQuestion(@PathVariable long id, @RequestBody UpdateQuestionRequest updateQuestionRequest){
        QuestionResponse refreshedQuestion = this.iQuestionService.editQuestion(id, updateQuestionRequest);

        return ResponseEntity.status(HttpStatus.OK).body(refreshedQuestion);
    }


    @DeleteMapping("api/v1/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id){
        this.iQuestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

}
