package com.example.examprep.services;

import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.models.User;

import java.util.List;
import java.util.Optional;

public interface SolvedQuestionService {

    List<SolvedQuestion> findAll();

    void save(SolvedQuestion solvedQuestion);

    Optional<SolvedQuestion> findById(Long id);

    void delete(long id);


    List<SolvedQuestion> findAllByUser(User user);

    SolvedQuestion findByUserAndTask(User user, Task task);

    SolvedQuestion findByUserAndTaskId(User user, Long task);

    List<SolvedQuestion> findAllByUserAndCategory(User user, Long category);

    List<SolvedQuestion> findByDifficulty(Long difficulty);

    long getNum();

    long getNumByCategory(Long category_id);

    double calcAvgScore();

    double calcAvgScoreByCategory(Long categoryId);
}
