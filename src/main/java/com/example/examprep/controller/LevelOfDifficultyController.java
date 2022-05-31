package com.example.examprep.controller;

import com.example.examprep.models.LevelOfDifficulty;
import com.example.examprep.services.LevelOfDifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/difficulty")
public class LevelOfDifficultyController {

    @Autowired
    private LevelOfDifficultyService difficultyService;

    @PostMapping(path = "/add")
    public LevelOfDifficulty addDifficulty(@RequestBody LevelOfDifficulty difficulty) {
        return difficultyService.save(difficulty);
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDifficulty(@RequestBody LevelOfDifficulty difficulty) {
        difficultyService.delete(difficulty.getId());
    }

    @GetMapping(path = "/all")
    public Iterable<LevelOfDifficulty> getAllDifficulties() {
        return difficultyService.findAll();
    }


}
