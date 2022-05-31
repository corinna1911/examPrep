package com.example.examprep.controller;

import com.example.examprep.dtos.dtos.ExamAddDto;
import com.example.examprep.models.Exam;
import com.example.examprep.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {


    private ExamService examService;

    @Autowired
    public ExamController(ExamService service){
        this.examService = service;
    }

    @PostMapping(path = "/add")
    public String addExam(@RequestBody ExamAddDto exam) {
        //examService.save(exam);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<Exam> getAllExams() {
        return examService.findAll();
    }


}
