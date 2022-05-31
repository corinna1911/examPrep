package com.example.examprep.repositories;


import com.example.examprep.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    @Override
    LinkedList<Solution> findAll();

}
