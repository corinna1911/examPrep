package com.example.examprep.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Aid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany
    List<DBFile> files;
    @ManyToOne
    Exam exam;
    @OneToOne
    ExerciseType exerciseType;


    public Aid(List<DBFile> files){
        this.files = files;
    }

    public Aid(){

    }
}
