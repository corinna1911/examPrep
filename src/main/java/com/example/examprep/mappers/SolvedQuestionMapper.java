package com.example.examprep.mappers;

import com.example.examprep.dtos.dtos.SolvedQuestionDto;
import com.example.examprep.models.LevelOfDifficulty;
import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import com.example.examprep.services.LevelOfDifficultyService;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.TaskService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SolvedQuestionMapper {

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @Autowired
    SolvedQuestionService solvedQuestionService;
    @Autowired
    LevelOfDifficultyService levelOfDifficultyService;

    public SolvedQuestion map(SolvedQuestionDto dto) {
        Task task = taskService.findById(Long.parseLong(dto.getTask())).get();
        User user = userService.findCurrentUser();
        SolvedQuestion solvedQuestion = solvedQuestionService.findByUserAndTask(user, task);
        LevelOfDifficulty difficulty = levelOfDifficultyService.findByDifficulty(dto.getDifficulty());

        if (solvedQuestion != null) {
            solvedQuestion.setLocalDateTime(LocalDateTime.now());
            solvedQuestion.setLevel(difficulty);
            return solvedQuestion;
        } else {
            return new SolvedQuestion(task, difficulty, user);
        }
    }

}
