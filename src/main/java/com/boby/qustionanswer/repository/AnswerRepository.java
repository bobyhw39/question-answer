package com.boby.qustionanswer.repository;

import com.boby.qustionanswer.dto.AnswerGetDTO;
import com.boby.qustionanswer.dto.AnswerGetTotalDTO;
import com.boby.qustionanswer.dto.AnswerPostDTO;
import com.boby.qustionanswer.dto.AnswerToQuestionIgnore;
import com.boby.qustionanswer.entity.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository {
    List<Answer> findAnswerById(Long id);
    void saveAnswer(AnswerPostDTO answerPostDTO);
    List<AnswerGetDTO> findAnswerByQuestionId(Long id);
    AnswerGetTotalDTO getTotalAnswerFromQuestion(Long id);
}
