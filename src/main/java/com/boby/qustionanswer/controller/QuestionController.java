package com.boby.qustionanswer.controller;

import com.boby.qustionanswer.dto.QuestionGetDTO;
import com.boby.qustionanswer.dto.QuestionPostDTO;
import com.boby.qustionanswer.entity.Question;
import com.boby.qustionanswer.exceptions.ErrorDetails;
import com.boby.qustionanswer.services.QuestionServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@Api(value = "Question Answer applications")
@RequestMapping("question")
@Validated
public class QuestionController {

    @Autowired
    QuestionServices questionServices;

    @PostMapping("")
    @ApiOperation(value = "Asked a new Question", response = ErrorDetails.class)
    public ErrorDetails askQuestion(@RequestBody QuestionPostDTO question){
        questionServices.saveQuestion(question);
        return new ErrorDetails(new Date(System.currentTimeMillis()), "question " + question.getTitle() + " asked", "200", "/question");
    }

    @GetMapping("")
    @ApiOperation(value = "Get All Question", response = QuestionGetDTO.class)
    public List<QuestionGetDTO> getAllQuestion(){
        return questionServices.getAllQuestion();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Question by Id", response = QuestionGetDTO.class)
    public QuestionGetDTO getQuestionById(@PathVariable Long id){
        return questionServices.getQuestionById(id);
    }
}
