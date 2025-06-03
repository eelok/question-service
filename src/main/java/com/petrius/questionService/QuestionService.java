package com.petrius.questionService;

import com.petrius.questionService.exeption.QuestionExistsException;
import com.petrius.questionService.exeption.QuestionIsMandatoryException;
import com.petrius.questionService.exeption.QuestionMustContainAnswerException;
import com.petrius.questionService.exeption.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question createQuestion(Question question) {
        if(question.getAnswers() == null || question.getAnswers().isEmpty()){
            throw new QuestionMustContainAnswerException("question must contain answers");
        }
        Question foundQuestion = this.questionsRepository.findByQuestionText(question.getQuestionText().trim());
        if(foundQuestion != null){
            throw new QuestionExistsException("such question is already exists");
        }
        Question savedQuestion = this.questionsRepository.saveAndFlush(question);

        question
                .getAnswers()
                .forEach(a -> {
                    a.setQuestion(savedQuestion);
                    this.answerRepository.saveAndFlush(a);
                });

        return savedQuestion;
    }

    @Override
    public List<QuestionResponse> getAll() {
        List<Question> questions = this.questionsRepository.findAll();
        List<QuestionResponse> allQuestion = questions
                .stream()
                .map(q -> this.questionMapper.toQuestionResponse(q))
                .toList();

        return allQuestion;
    }


    @Override
    public QuestionResponse getById(long id) {

        Question question = this.questionsRepository
                .findById(id)
                .orElseThrow(
                        () -> new RecordNotFoundException("question with id: " + id + " was not found")
                );
        return this.questionMapper.toQuestionResponse(question);
    }

    @Override
    public Question findById(long id) {
        //todo remove
        return null;
    }


    @Override
    public Question editQuestion(long id, Question question) {
        if(question.getQuestionText() == null || question.getQuestionText().trim().isEmpty()){
            throw new QuestionIsMandatoryException("question texts can not be empty");
        }
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
    }

    @Override
    public Question findByQuestionText(String name) {
       return this.questionsRepository.findByQuestionText(name);
    }


}
