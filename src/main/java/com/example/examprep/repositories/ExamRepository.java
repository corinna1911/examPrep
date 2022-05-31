package com.example.examprep.repositories;

import com.example.examprep.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Override
    LinkedList<Exam>findAll();

    Exam findById(long id);



}
