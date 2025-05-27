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
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question){
            Question createdQuestion = this.iQuestionService.createQuestion(question);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @GetMapping("api/v1/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = this.iQuestionService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("api/v1/questions/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable long id){
        Question question = this.iQuestionService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @PutMapping("api/v1/questions/{id}")
    public ResponseEntity<Question> editQuestion(@PathVariable long id, @RequestBody Question question){
        Question refreshedQuestion = this.iQuestionService.editQuestion(id, question);

        return ResponseEntity.status(HttpStatus.OK).body(refreshedQuestion);
    }


    @DeleteMapping("api/v1/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id){
        this.iQuestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

}
