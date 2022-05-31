package com.example.examprep.services;

import com.example.examprep.models.Solution;

import java.util.List;
import java.util.Optional;

public interface SolutionService {

    List<Solution> findAll();

    Solution save(Solution solution);

    Optional<Solution> findById(Long id);

    void delete(long id);

}
