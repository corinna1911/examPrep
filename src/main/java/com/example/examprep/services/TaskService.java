package com.example.examprep.services;


import com.example.examprep.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    void save(Task task);

    Optional<Task> findById(Long id);

    void delete(long id);

    Task getNextTask(Long category);

    List<Task> findByCategory(Long id);

    List<Task> findByExam(Long id);

}
