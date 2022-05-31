package com.example.examprep.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    private List<Task> task;
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private LevelOfDifficulty difficulty;

}
