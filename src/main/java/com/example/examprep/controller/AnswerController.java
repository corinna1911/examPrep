package com.example.examprep.controller;

import com.example.examprep.models.Answer;
import com.example.examprep.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(path = "/add")
    public void addAnswer(@Nullable @RequestParam ArrayList<MultipartFile> file, @Nullable @RequestParam String answer, @RequestParam long taskId) throws IOException {
        answerService.addAnswer(file, answer, taskId);
    }


    @DeleteMapping(path = "/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAnswer(@RequestBody Long answerId) {
        answerService.delete(answerId);
    }

    @GetMapping(path = "/byTaskAndUser")
    public Optional<Answer> findByTaskAndUser(@RequestParam long task) {
        return Optional.ofNullable(answerService.findByTaskAndUser(task));
    }

    @GetMapping(path = "/all")
    public Iterable<Answer> getAllAnswers() {
        return answerService.findAll();
    }

}
