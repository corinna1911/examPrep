package com.example.examprep.services;

import com.example.examprep.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    void save(Category category);

    Optional<Category> findById(Long id);

    void delete(long id);

}
