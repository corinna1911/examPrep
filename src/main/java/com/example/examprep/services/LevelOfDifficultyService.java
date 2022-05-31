package com.example.examprep.services;

import com.example.examprep.models.LevelOfDifficulty;

import java.util.List;
import java.util.Optional;

public interface LevelOfDifficultyService {

    List<LevelOfDifficulty> findAll();

    LevelOfDifficulty save(LevelOfDifficulty difficulty);

    Optional<LevelOfDifficulty> findById(Long id);

    void delete(long id);

    LevelOfDifficulty findByDifficulty(String level);
}
