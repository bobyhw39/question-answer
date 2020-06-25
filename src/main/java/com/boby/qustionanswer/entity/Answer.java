package com.boby.qustionanswer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_answer")
@Setter
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_by", nullable = false)
    private String answer_by;

    @Column(name = "the_answer", nullable = false)
    private String theAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @Getter(onMethod = @__( @JsonIgnore))
    @Setter
    private Question question;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime= LocalDateTime.now();
}
