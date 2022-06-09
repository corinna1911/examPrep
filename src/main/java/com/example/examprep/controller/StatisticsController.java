package com.example.examprep.controller;

import com.example.examprep.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    @GetMapping(path = "/score")
    public double getAverageScore() {
        return statisticsService.getAvgScore();
    }

    @GetMapping(path = "/score/byCategory")
    public double getAverageScoreByCategory(@RequestParam Long categoryId) {
        return statisticsService.getAvgScoreByCategory(categoryId);
    }

    @GetMapping(path = "/solved")
    public double getPercentageSolved() {
        return statisticsService.getPercentageSolved();
    }

    @GetMapping(path = "/solved/byCategory")
    public double getPercentageSolvedByCategory(@RequestParam Long categoryId){
        return statisticsService.getPercentageSolvedByCategory(categoryId);
    }

    @GetMapping(path = "/duration")
    public long getStudyDuration(HttpServletRequest request){
        return statisticsService.updateDuration((LocalDateTime) request.getSession().getAttribute("DateTime")).toMinutes();
    }

}
