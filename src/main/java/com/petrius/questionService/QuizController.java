package com.petrius.questionService;

import com.petrius.questionService.entity.Question;
import com.petrius.questionService.exeption.RecordNotFoundException;
import com.petrius.questionService.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionsRepository questionsRepository;

    @PostMapping("/api/v1/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        Quiz createdQuiz = this.quizRepository.saveAndFlush(quiz);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @PostMapping("/api/v1/quizzes/{quizId}/questions/{questionId}")
    public ResponseEntity<Void> addQuestionToQuiz(
            @PathVariable Long quizId,
            @PathVariable Long questionId
    ){
        Quiz quiz = this.quizRepository.findById(quizId)
                .orElseThrow(() -> new RecordNotFoundException("quiz with Id: " + quizId + " was not found"));

        Question question = this.questionsRepository.findById(questionId)
                .orElseThrow(() -> new RecordNotFoundException("question with id: " + questionId+ " was not found"));

        quiz.getQuestions().add(question);
        this.quizRepository.saveAndFlush(quiz);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/api/v1/quizzes/{quizId}")
    public ResponseEntity<Quiz> getQuizWithQuestions(@PathVariable Long quizId){
        Quiz quiz = this.quizRepository.findById(quizId)
                .orElseThrow(() -> new RecordNotFoundException("quiz with Id: " + quizId + " was not found"));
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    @GetMapping("/api/v1/quizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        List<Quiz> quizzes = this.quizRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(quizzes);
    }

    @DeleteMapping("/api/v1/quizzes/{quizId}/questions/{questionId}")
    public ResponseEntity<Void> removeQuestionFromQuiz(
            @PathVariable Long quizId,
            @PathVariable Long questionId
    ){
        Quiz quiz = this.quizRepository.findById(quizId)
                .orElseThrow(() -> new RecordNotFoundException("quiz with Id: " + quizId + " was not found")
        );

        Question question = this.questionsRepository.findById(questionId)
                .orElseThrow(() -> new RecordNotFoundException("question with Id: " + questionId + " was not found"));

        if(!quiz.getQuestions().contains(question)){
            throw new RecordNotFoundException("question with id: " + questionId + " was not found in quiz " + quizId);
        }

        quiz.getQuestions().remove(question);

        this.quizRepository.saveAndFlush(quiz);
        return ResponseEntity.noContent().build();

    }
}

