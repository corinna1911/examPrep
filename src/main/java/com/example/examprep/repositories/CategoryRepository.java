package com.example.examprep.repositories;

import com.example.examprep.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    LinkedList<Category> findAll();

}
