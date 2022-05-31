package com.example.examprep.controller;

import com.example.examprep.dtos.dtos.SolvedQuestionDto;
import com.example.examprep.mappers.SolvedQuestionMapper;
import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.services.LevelOfDifficultyService;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.TaskService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/solved")
public class SolvedQuestionController {

    @Autowired
    SolvedQuestionMapper mapper;
    @Autowired
    private SolvedQuestionService solvedQuestionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private LevelOfDifficultyService difficultyService;

    /**
     * @PostMapping(path = "/add")
     * public String addSolvedQuestion(@RequestBody SolvedQuestion solvedQuestion, HttpServletRequest request) {
     * String user = (String) request.getSession().getAttribute("username");
     * if(solvedQuestionService.findByUserAndTask(userService.findByUsername(user), taskService.findById(solvedQuestion.getTask().getId().get())) == null);
     * <p>
     * solvedQuestion.setUser(userService.findByUsername(user));
     * solvedQuestion.setLevel(difficultyService.findByDifficulty(solvedQuestion.getLevel().getLevel()));
     * solvedQuestion.setTask(taskService.findById(solvedQuestion.getTask().getId()).get());
     * solvedQuestion.setLocalDateTime(LocalDateTime.now());
     * <p>
     * solvedQuestionService.save(solvedQuestion);
     * return "Saved";
     * }
     */

    @PostMapping(path = "/add")
    public String addSolvedQuestion(@RequestBody SolvedQuestionDto solvedQuestionDto) {
        solvedQuestionService.save(mapper.map(solvedQuestionDto));
        return "Saved";
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSolvedQuestion(@RequestParam Long solvedQuestionId) {
        solvedQuestionService.delete(solvedQuestionId);
    }

    @GetMapping(path = "/all")
    public Iterable<SolvedQuestion> getAllSolvedQuestion() {
        return solvedQuestionService.findAll();
    }

    @GetMapping(path = "/getById")
    public Optional getById(long id) {
        return solvedQuestionService.findById(id);
    }

    @GetMapping(path = "/difficulty")
    public List<SolvedQuestion> getByDifficulty(@RequestParam Long difficulty) {
        return solvedQuestionService.findByDifficulty(difficulty);
    }
}
