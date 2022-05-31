package com.example.examprep.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "LONGTEXT")
    private String answer;
    @OneToOne
    private DBFile graphic;
    @ManyToOne()
    private Task task;


    public Answer(String answer, DBFile graphic, Task task) {
        this.answer = answer;
        this.graphic = graphic;
        this.task = task;
    }
}
