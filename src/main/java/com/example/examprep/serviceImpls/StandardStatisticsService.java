package com.example.examprep.serviceImpls;

import com.example.examprep.models.User;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.StatisticsService;
import com.example.examprep.services.TaskService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class StandardStatisticsService implements StatisticsService {

    private TaskService taskService;
    private SolvedQuestionService solvedQuestionService;
    private UserService userService;

    @Autowired
    public StandardStatisticsService(TaskService taskService, SolvedQuestionService solvedQuestionService, UserService userService) {
        this.taskService = taskService;
        this.solvedQuestionService = solvedQuestionService;
        this.userService = userService;
    }

    @Override
    public double getAvgScore() {
        return solvedQuestionService.calcAvgScore();
    }

    @Override
    public double getAvgScoreByCategory(Long categoryId) {
        return solvedQuestionService.calcAvgScoreByCategory(categoryId);
    }

    @Override
    public double getPercentageSolved() {
        long numTasks = taskService.getNum();
        long numSolvedTasks = solvedQuestionService.getNum();

        if (numTasks > 0) {
            return (double) numSolvedTasks / numTasks * 100;
        }
        return 0;
    }

    @Override
    public double getPercentageSolvedByCategory(Long category_id) {
        long numTasksByCategory = taskService.getNumByCategory(category_id);
        long numSolvedTasksByCategory = solvedQuestionService.getNumByCategory(category_id);

        if (numTasksByCategory > 0) {
            return (double) numSolvedTasksByCategory / numTasksByCategory * 100;
        }
        return 0;
    }


    @Override
    public Duration updateDuration(LocalDateTime loginTime, User user) {
        long additionalSecondes = ChronoUnit.SECONDS.between(loginTime, LocalDateTime.now());
        Duration newDuration = user.getDuration().plusSeconds(additionalSecondes);


        user.setDuration(newDuration);
        userService.save(user);

        return user.getDuration();
    }

    @Override
    public Duration updateDuration(LocalDateTime loginTime) {
        return updateDuration(loginTime, userService.findCurrentUser());
    }


}
