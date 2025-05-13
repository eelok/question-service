package com.petrius.questionService;

import com.petrius.questionService.exeption.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/questions")
public class QuestionController {


    private QuestionsRepository questionsRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public QuestionController(QuestionsRepository questionsRepository, AnswerRepository answerRepository) {
        this.questionsRepository = questionsRepository;
        this.answerRepository = answerRepository;
    }

    @PostMapping("api/v1/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question savedQuestion = this.questionsRepository.saveAndFlush(question);

        if(question.getAnswers() != null && !question.getQuestionText().isEmpty()){
            question
                    .getAnswers()
                    .forEach(a -> {
                        a.setQuestion(question);
                        this.answerRepository.saveAndFlush(a);
                    });
            savedQuestion = this.questionsRepository.getReferenceById(savedQuestion.getId());

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    @GetMapping("api/v1/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = this.questionsRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("api/v1/questions/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable long id){
        Optional<Question> question = this.questionsRepository.findById(id);
        if(question.isEmpty()){
            throw new RecordNotFoundException("question with id: " + id +" was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(question.get());
    }


    @DeleteMapping("api/v1/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id){
        Optional<Question> question = this.questionsRepository.findById(id);
        if(question.isEmpty()){
            throw new RecordNotFoundException("question with id: " + id +" was not found");
        }
        List<Answer> answers = question.get().getAnswers();
        List<Long> answerIds = answers
                .stream()
                .map(Answer::getId)
                .toList();

        this.answerRepository.deleteAllById(answerIds);
        this.questionsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
