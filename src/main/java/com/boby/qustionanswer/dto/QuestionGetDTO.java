package com.boby.qustionanswer.dto;

import com.boby.qustionanswer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGetDTO {
    private Long id;
    private String title;
    private String details;
    private String askedBy;
    private Timestamp dateTime;
    private Integer totalAnswer;
}
