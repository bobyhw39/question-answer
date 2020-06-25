package com.boby.qustionanswer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_question")
@Setter
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "asked_by", nullable = false)
    private String askedBy;

    @OneToMany(mappedBy = "question")
    private List<Answer> answer;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime= LocalDateTime.now();
}
