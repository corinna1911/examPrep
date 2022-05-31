package com.example.examprep.controller;

import com.example.examprep.models.ExamType;
import com.example.examprep.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/examtype")
public class ExamTypeController {

    @Autowired
    private ExamTypeService examTypeService;

    @GetMapping(path = "/all")
    public List<ExamType> findAll(){
        return examTypeService.findAll();
    }

}
