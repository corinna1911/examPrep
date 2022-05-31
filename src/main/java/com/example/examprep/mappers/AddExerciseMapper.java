package com.example.examprep.mappers;

import com.example.examprep.dtos.dtos.AddTaskDto;
import com.example.examprep.models.Task;
import com.example.examprep.services.LevelOfDifficultyService;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.TaskService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddExerciseMapper {

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @Autowired
    SolvedQuestionService solvedQuestionService;
    @Autowired
    LevelOfDifficultyService levelOfDifficultyService;

    public Task map(AddTaskDto dto) {
        Task task = new Task(dto.getTask(), dto.getPoints());
        return task;
    }

}

