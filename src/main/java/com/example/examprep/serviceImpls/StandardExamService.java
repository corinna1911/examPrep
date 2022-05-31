package com.example.examprep.serviceImpls;

import com.example.examprep.models.Exam;
import com.example.examprep.repositories.ExamRepository;
import com.example.examprep.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class StandardExamService implements ExamService {

    @Autowired
    ExamRepository examRepository;

    @Override
    public LinkedList<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(long id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

}
