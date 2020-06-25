package com.boby.qustionanswer.repository.dao;

import com.boby.qustionanswer.dto.AnswerGetTotalDTO;
import com.boby.qustionanswer.dto.QuestionGetDTO;
import com.boby.qustionanswer.entity.Question;
import com.boby.qustionanswer.repository.AnswerRepository;
import com.boby.qustionanswer.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class QuestionDao implements QuestionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public void saveQuestion(Question question) {
        jdbcTemplate.update(
                "insert into tbl_question (asked_by,date,details,title) values (?,?,?,?) ",
                question.getAskedBy(),question.getDateTime(),question.getDetails(),question.getTitle()
        );
    }

    private Integer getTotalAnswer(Long id){
        String sql = "SELECT count(the_answer) FROM db_qanda.tbl_answer where question_id=?";
        AnswerGetTotalDTO abc =  jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum) -> new AnswerGetTotalDTO(rs.getInt("count(the_answer)")));
        return abc.getTotal();
    }

    @Override
    public List<QuestionGetDTO> getAllQuestion() {

        return jdbcTemplate.query("select * from tbl_question",
                (rs, rowNum) -> new QuestionGetDTO(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("details"),
                        rs.getString("asked_by"),
                        rs.getTimestamp("date"),
                        getTotalAnswer(rs.getLong("id"))

                )
        );
    }

    @Override
    public QuestionGetDTO getQuestionById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from tbl_question where id=?",
                new Object[]{id},
                (rs, rowNum) -> new QuestionGetDTO(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("details"),
                        rs.getString("asked_by"),
                        rs.getTimestamp("date"),
                        getTotalAnswer(rs.getLong("id"))
                )
        );
    }
}
