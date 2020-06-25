package com.boby.qustionanswer.services.impl;

import com.boby.qustionanswer.dto.QuestionGetDTO;
import com.boby.qustionanswer.dto.QuestionPostDTO;
import com.boby.qustionanswer.entity.Question;
import com.boby.qustionanswer.exceptions.NotFoundException;
import com.boby.qustionanswer.repository.QuestionRepository;
import com.boby.qustionanswer.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServicesImpl implements QuestionServices {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserServicesImpl userServices;

    @Override
    public void saveQuestion(QuestionPostDTO question) {
        userServices.findByUsername(question.getAskedBy());
        Question question1 = new Question(null,question.getTitle(),question.getDetails(),question.getAskedBy(),null, LocalDateTime.now());
        questionRepository.saveQuestion(question1);
    }

    @Override
    public List<QuestionGetDTO> getAllQuestion() {
        try {
            return questionRepository.getAllQuestion();
        } catch (EmptyResultDataAccessException x){
            List<QuestionGetDTO> questionGetDTOS = new ArrayList<>();
            return questionGetDTOS;
        }
    }

    @Override
    public QuestionGetDTO getQuestionById(Long id) {
        try{
            return questionRepository.getQuestionById(id);
        } catch (EmptyResultDataAccessException x){
            throw new NotFoundException("question " + id + " not found");
        }
    }
}
