package com.example.examprep.services;

import com.example.examprep.models.Exam;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

public interface ExamService {

    LinkedList<Exam> findAll();

    Exam findById(long id);

    Exam save(Exam exam);
}
