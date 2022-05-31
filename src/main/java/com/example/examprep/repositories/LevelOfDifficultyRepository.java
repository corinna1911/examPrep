package com.example.examprep.repositories;

import com.example.examprep.models.LevelOfDifficulty;
import com.example.examprep.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface LevelOfDifficultyRepository extends JpaRepository<LevelOfDifficulty, Long> {

    @Override
    LinkedList<LevelOfDifficulty> findAll();

    LevelOfDifficulty findByLevel(String difficulty);
}
