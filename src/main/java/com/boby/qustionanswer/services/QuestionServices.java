package com.boby.qustionanswer.services;

import com.boby.qustionanswer.dto.QuestionGetDTO;
import com.boby.qustionanswer.dto.QuestionPostDTO;
import com.boby.qustionanswer.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionServices {
    void saveQuestion(QuestionPostDTO question);
    List<QuestionGetDTO> getAllQuestion();
    QuestionGetDTO getQuestionById(Long id);
}
