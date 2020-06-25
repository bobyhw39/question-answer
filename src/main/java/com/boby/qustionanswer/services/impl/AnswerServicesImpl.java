package com.boby.qustionanswer.services.impl;

import com.boby.qustionanswer.dto.AnswerGetDTO;
import com.boby.qustionanswer.dto.AnswerGetTotalDTO;
import com.boby.qustionanswer.dto.AnswerPostDTO;
import com.boby.qustionanswer.repository.AnswerRepository;
import com.boby.qustionanswer.services.AnswerServices;
import com.boby.qustionanswer.services.QuestionServices;
import com.boby.qustionanswer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServicesImpl implements AnswerServices {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    UserServices userServices;

    @Override
    public void saveAnswer(AnswerPostDTO answerPostDTO) {
        userServices.findByUsername(answerPostDTO.getAnswer_by());
        questionServices.getQuestionById(answerPostDTO.getQuestionId());
        answerRepository.saveAnswer(answerPostDTO);
    }

    @Override
    public List<AnswerGetDTO> getAllAnswerByQuestionId(Long id) {
        return answerRepository.findAnswerByQuestionId(id);
    }

    @Override
    public AnswerGetTotalDTO getTotalAnswerFromQuestion(Long id) {
        questionServices.getQuestionById(id);
        return answerRepository.getTotalAnswerFromQuestion(id);
    }
}
