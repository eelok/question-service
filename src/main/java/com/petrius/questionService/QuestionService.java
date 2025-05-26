package com.petrius.questionService;

import com.petrius.questionService.exeption.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Question createQuestion(Question question) {
        Question savedQuestion = this.questionsRepository.saveAndFlush(question);

        if (question.getAnswers() != null && !question.getQuestionText().isEmpty()) {
            question
                    .getAnswers()
                    .forEach(a -> {
                        a.setQuestion(question);
                        this.answerRepository.saveAndFlush(a);
                    });
            return this.questionsRepository.getReferenceById(savedQuestion.getId());
        };
        //todo improve the exception
        throw new RuntimeException("question must contain answers");
    }

    @Override
    public List<Question> findAll() {
        return this.questionsRepository.findAll();
    }

    @Override
    public Question findById(long id) {
        return this.questionsRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("question with id: " + id + " was not found"));
    }

    @Override
    public Question editQuestion(long id, Question question) {
        Question questionToUpdate = findById(id);

        questionToUpdate.setQuestionText(question.getQuestionText());

        this.questionsRepository.saveAndFlush(questionToUpdate);

        List<Answer> existingAnswers = questionToUpdate.getAnswers();

        for(int i = 0; i < existingAnswers.size(); i++){
            Answer existingAnswer = existingAnswers.get(i);
            Answer newAnswer = question.getAnswers().get(i);
            existingAnswer.setAnswerText(newAnswer.getAnswerText());
            existingAnswer.setCorrect(newAnswer.getCorrect());

            this.answerRepository.saveAndFlush(existingAnswer);
        }

        return findById(id);
    }

    @Override
    public void deleteQuestion(long id) {
        Question question = findById(id);
        List<Answer> answers = question.getAnswers();
        List<Long> answerIds = answers
                .stream()
                .map(Answer::getId)
                .toList();

        this.answerRepository.deleteAllById(answerIds);
        this.questionsRepository.deleteById(id);
    };
}
