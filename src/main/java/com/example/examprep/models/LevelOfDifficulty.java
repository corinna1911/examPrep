package com.example.examprep.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LevelOfDifficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String level;
    private int levelNumber;

    public LevelOfDifficulty(String level){
        this.level = level;
    }
}
