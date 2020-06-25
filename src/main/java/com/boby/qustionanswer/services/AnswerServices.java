package com.boby.qustionanswer.services;

import com.boby.qustionanswer.dto.AnswerGetDTO;
import com.boby.qustionanswer.dto.AnswerGetTotalDTO;
import com.boby.qustionanswer.dto.AnswerPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerServices {
    void saveAnswer(AnswerPostDTO answerPostDTO);
    List<AnswerGetDTO> getAllAnswerByQuestionId(Long id);
    AnswerGetTotalDTO getTotalAnswerFromQuestion(Long id);
}
