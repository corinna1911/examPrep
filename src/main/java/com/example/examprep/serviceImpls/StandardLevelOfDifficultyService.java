package com.example.examprep.serviceImpls;

import com.example.examprep.models.LevelOfDifficulty;
import com.example.examprep.repositories.LevelOfDifficultyRepository;
import com.example.examprep.services.LevelOfDifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class StandardLevelOfDifficultyService implements LevelOfDifficultyService {

    @Autowired
    private LevelOfDifficultyRepository difficultyRepository;

    @Override
    public Optional<LevelOfDifficulty> findById(Long id) {
        return difficultyRepository.findById(id);
    }

    @Override
    public LevelOfDifficulty save(LevelOfDifficulty difficulty) {
        return difficultyRepository.save(difficulty);
    }

    @Override
    public LinkedList<LevelOfDifficulty> findAll() {
        return difficultyRepository.findAll();
    }

    @Override
    public void delete(long id) {
        difficultyRepository.deleteById(id);
    }

    @Override
    public LevelOfDifficulty findByDifficulty(String level) {
        return difficultyRepository.findByLevel(level);
    }

}
