package com.example.examprep.controller;

import com.example.examprep.models.Solution;
import com.example.examprep.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @PostMapping(path = "/add")
    public String addSolution(@RequestBody Solution solution) {
        solutionService.save(solution);
        return "Saved";
    }

    @DeleteMapping(path = "/delete")
    public void deleteSolution(@RequestParam Long solutionId) {
        solutionService.delete(solutionId);
    }

    @GetMapping(path = "/all")
    public Iterable<Solution> getAllSolutions() {
        return solutionService.findAll();
    }

    @GetMapping(path = "/getById")
    public Optional getById(long id){
        return solutionService.findById(id);
    }


}
