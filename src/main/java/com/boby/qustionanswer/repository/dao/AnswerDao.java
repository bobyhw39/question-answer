package com.boby.qustionanswer.repository.dao;

import com.boby.qustionanswer.dto.*;
import com.boby.qustionanswer.entity.Answer;
import com.boby.qustionanswer.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnswerDao implements AnswerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Answer> findAnswerById(Long id) {
        return jdbcTemplate.queryForList(
                "select * from tbl_answer where id=?",
                Answer.class
        );
    }

    @Override
    public void saveAnswer(AnswerPostDTO answerPostDTO) {
        jdbcTemplate.update(
                "insert into tbl_answer (answer_by,the_answer,question_id,date) values (?,?,?,?)",
                answerPostDTO.getAnswer_by(),answerPostDTO.getTheAnswer(),answerPostDTO.getQuestionId(), LocalDateTime.now()
        );
    }

    @Override
    public List<AnswerGetDTO> findAnswerByQuestionId(Long id) {
        return jdbcTemplate.query(
                "select * from tbl_answer where question_id=?", new Object[]{id}, new ResultSetExtractor<List<AnswerGetDTO>>() {
                    @Override
                    public List<AnswerGetDTO> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<AnswerGetDTO> answerGetDTOS = new ArrayList<>();
                        while (resultSet.next()){
                            answerGetDTOS.add(
                                    new AnswerGetDTO(
                                            resultSet.getLong("id"),
                                            resultSet.getString("answer_by"),
                                            resultSet.getString("the_answer"),
                                            resultSet.getLong("question_id"),
                                            resultSet.getTimestamp("date")
                                    )
                            );
                        }
                        return answerGetDTOS;
                    }
                }
        );
    }

    @Override
    public AnswerGetTotalDTO getTotalAnswerFromQuestion(Long id) {
        String sql = "SELECT count(the_answer) FROM db_qanda.tbl_answer where question_id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum) -> new AnswerGetTotalDTO(rs.getInt("count(the_answer)")));
    }
}
