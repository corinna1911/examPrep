package com.example.examprep.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SolvedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answer;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Date date;
    private double score;
    @ManyToOne
    private LevelOfDifficulty level;
    private LocalDateTime localDateTime;

    public SolvedQuestion(Task task, LevelOfDifficulty level){
        this.task = task;
        this.level = level;
    }

    public SolvedQuestion(Task task, LevelOfDifficulty level, User user){
        this.task = task;
        this.level = level;
        this.user = user;
    }

    public SolvedQuestion(Task task, Answer answer, User user){
        this.task = task;
        this.answer = answer;
        this.user = user;
    }

}
