package com.example.examprep.repositories;


import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    LinkedList<Task> findAll();


    List<Task> findByCategory_Id(Long id);

    List<Task> findByExam_Id(Long exam);

    @Query ("select distinct t FROM Task t where  t.category.id = :#{#category} and t.id not in (select s.task.id from SolvedQuestion s where s.user.id = :#{#user})")
    LinkedList<Task> findAllByNotUserAndCategory(@Param("user")Long user, @Param("category")Long category);

}
