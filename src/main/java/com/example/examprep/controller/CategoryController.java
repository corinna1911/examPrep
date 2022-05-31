package com.example.examprep.controller;

import com.example.examprep.models.Category;
import com.example.examprep.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/add")
    public String addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return "Saved";
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public String deleteCategory(@RequestParam Long categoryId) {
        categoryService.delete(categoryId);
        return "Deleted";
    }

    @GetMapping(path = "/all")
    public Iterable<Category> getAllCategories() {
        return categoryService.findAll();
    }

}
