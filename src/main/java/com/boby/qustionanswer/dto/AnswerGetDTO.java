package com.boby.qustionanswer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerGetDTO {
    private Long id;
    private String answer_by;
    private String theAnswer;
    private Long questionId;
    private Timestamp dateTime;
}
