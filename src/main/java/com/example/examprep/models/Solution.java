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
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "LONGTEXT")
    private String solution;
    @OneToOne
    private DBFile graphic;


    public Solution(String solution, DBFile graphic) {
        this.solution = solution;
        this.graphic = graphic;
    }
}
