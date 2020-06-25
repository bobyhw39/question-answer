package com.boby.qustionanswer.controller;

import com.boby.qustionanswer.dto.AnswerGetDTO;
import com.boby.qustionanswer.dto.AnswerGetTotalDTO;
import com.boby.qustionanswer.dto.AnswerPostDTO;
import com.boby.qustionanswer.exceptions.ErrorDetails;
import com.boby.qustionanswer.services.AnswerServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@Api(value = "Question Answer applications")
@RequestMapping("answer")
@Validated
public class AnswerController {

    @Autowired
    AnswerServices answerServices;

    @GetMapping("")
    public List<AnswerGetDTO> getAnswerByQuestionId(@RequestParam Long id){
        return answerServices.getAllAnswerByQuestionId(id);
    }

    @GetMapping("/total/{id}")
    public AnswerGetTotalDTO getTotalAnswerFromQuestion(@PathVariable Long id){
        return answerServices.getTotalAnswerFromQuestion(id);
    }

    @PostMapping("")
    public ErrorDetails saveAnswer(@RequestBody AnswerPostDTO answerPostDTO){
        answerServices.saveAnswer(answerPostDTO);
        return new ErrorDetails(new Date(System.currentTimeMillis()), answerPostDTO.getQuestionId() + " answered", "200", "/answer");
    }
}
