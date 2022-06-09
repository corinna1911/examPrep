package com.example.examprep.services;

import com.example.examprep.models.User;

import java.time.Duration;
import java.time.LocalDateTime;

public interface StatisticsService {

    double getAvgScore();

    double getPercentageSolved();

    Duration updateDuration(LocalDateTime loginTime, User user);

    Duration updateDuration(LocalDateTime loginTime);

    double getPercentageSolvedByCategory(Long categoryId);

    double getAvgScoreByCategory(Long categoryId);
}
