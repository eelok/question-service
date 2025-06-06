package com.petrius.questionService.service;

import com.petrius.questionService.entity.Answer;
import com.petrius.questionService.entity.Question;
import com.petrius.questionService.exeption.QuestionExistsException;
import com.petrius.questionService.exeption.QuestionIsMandatoryException;
import com.petrius.questionService.exeption.QuestionMustContainAnswerException;
import com.petrius.questionService.exeption.RecordNotFoundException;
import com.petrius.questionService.mapper.AnswerMapper;
import com.petrius.questionService.mapper.QuestionMapper;
import com.petrius.questionService.model.AnswerResponse;
import com.petrius.questionService.model.CreateQuestionRequest;
import com.petrius.questionService.model.QuestionResponse;
import com.petrius.questionService.repository.AnswerRepository;
import com.petrius.questionService.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private AnswerMapper answerMapper;


    @Transactional
    @Override
    public QuestionResponse createQuestion(CreateQuestionRequest questionRequest) {
        if(questionRequest.getAnswers() == null || questionRequest.getAnswers().isEmpty()){
            throw new QuestionMustContainAnswerException("question must contain answers");
        }
        Question foundQuestion = this.questionsRepository.findByQuestionText(questionRequest.getQuestionText().trim());
        if(foundQuestion != null){
            throw new QuestionExistsException("such question is already exists");
        }
        Question question = this.questionMapper.toQuestion(questionRequest);

        Question savedQuestion = this.questionsRepository.saveAndFlush(question);
        savedQuestion.getAnswers().forEach(a -> {
            a.setQuestion(savedQuestion);
            this.answerRepository.saveAndFlush(a);
        });

        return this.questionMapper.toQuestionResponse(savedQuestion);
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
    public QuestionResponse editQuestion(long id, AnswerResponse.UpdateQuestionRequest question) {
        if(question.getQuestionText() == null || question.getQuestionText().trim().isEmpty()){
            throw new QuestionIsMandatoryException("question texts can not be empty");
        }
        Question questionToUpdate = this.questionsRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException("question with id: " + id + " was not found"));

        questionToUpdate.setQuestionText(question.getQuestionText());
        this.questionsRepository.saveAndFlush(questionToUpdate);

        for(int i = 0; i < questionToUpdate.getAnswers().size(); i++){
            Answer answer = questionToUpdate.getAnswers().get(i);
            AnswerResponse.UpdateAnswerRequest update = question.getAnswers().get(i);
            answer.setAnswerText(update.getAnswerText());
            answer.setCorrect(update.getCorrect());
            answer.setQuestion(questionToUpdate);
            this.answerRepository.saveAllAndFlush(questionToUpdate.getAnswers());
        }

        return getById(questionToUpdate.getId());
    }


    @Override
    public void deleteQuestion(long id) {
        QuestionResponse question = getById(id);
        List<Long> answersId = question.getAnswers().stream().map(AnswerResponse::getId).toList();

        this.answerRepository.deleteAllById(answersId);
        this.questionsRepository.deleteById(id);
    }

    @Override
    public Question findByQuestionText(String name) {
       return this.questionsRepository.findByQuestionText(name);
    }


}
