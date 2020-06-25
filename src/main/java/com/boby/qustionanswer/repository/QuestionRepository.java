package com.boby.qustionanswer.repository;

import com.boby.qustionanswer.dto.QuestionGetDTO;
import com.boby.qustionanswer.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository {
    void saveQuestion(Question question);
    List<QuestionGetDTO> getAllQuestion();
    QuestionGetDTO getQuestionById(Long id);
}
