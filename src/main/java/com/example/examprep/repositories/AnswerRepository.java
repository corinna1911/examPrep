package com.example.examprep.repositories;

import com.example.examprep.models.Answer;
import com.example.examprep.models.Category;
import com.example.examprep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Override
    LinkedList<Answer> findAll();
}
