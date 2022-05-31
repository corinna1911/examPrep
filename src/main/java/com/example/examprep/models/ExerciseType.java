package com.example.examprep.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String description;

}
