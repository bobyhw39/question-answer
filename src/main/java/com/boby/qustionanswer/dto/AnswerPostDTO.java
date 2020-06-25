package com.boby.qustionanswer.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPostDTO {
    private String answer_by;
    private String theAnswer;
    private Long questionId;
}
