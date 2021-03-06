package com.example.examprep.controller;

import com.example.examprep.models.DBFile;
import com.example.examprep.models.Solution;
import com.example.examprep.models.Task;
import com.example.examprep.services.FileService;
import com.example.examprep.services.SolutionService;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskService taskService;
    private FileService fileService;
    private SolutionService solutionService;

    @Autowired
    public TaskController(FileService fileService, TaskService taskService, SolutionService solutionService) {
        this.fileService = fileService;
        this.taskService = taskService;
        this.solutionService = solutionService;
    }

    @PostMapping(path = "/add")
    public Task add(@RequestParam ArrayList<MultipartFile> file, @RequestParam String task, @RequestParam double points, @RequestParam String solution) throws IOException {
        DBFile taskFile = fileService.storeFile(file.get(0));
        DBFile solutionFile = fileService.storeFile(file.get(1));

        Solution solutionId = solutionService.save(new Solution(solution, solutionFile));

        return taskService.save(new Task(task, points, taskFile, solutionId));
    }


    @DeleteMapping(path = "/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@RequestParam Long taskId) {
        taskService.delete(taskId);
    }

    @GetMapping(path = "/all")
    public Iterable<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping(path = "/next")
    public Task getNextTask(@RequestParam Long category) {
        return taskService.getNextTask(category);
    }

    @GetMapping(path = "/byCategory")
    public List<Task> getByCategory(@RequestParam Long id) {
        return taskService.findByCategory(id);
    }

    @GetMapping(path = "/byExam")
    public List<Task> getByExam(@RequestParam Long id) {
        return taskService.findByExam(id);
    }

}
