package com.example.examprep.serviceImpls;

import com.example.examprep.models.Solution;
import com.example.examprep.repositories.SolutionRepository;
import com.example.examprep.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class StandardSolutionService implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public Optional<Solution> findById(Long id) {
        return solutionRepository.findById(id);
    }

    @Override
    public Solution save(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public LinkedList<Solution> findAll() {
        return solutionRepository.findAll();
    }

    @Override
    public void delete(long id) {
        solutionRepository.deleteById(id);
    }


}
