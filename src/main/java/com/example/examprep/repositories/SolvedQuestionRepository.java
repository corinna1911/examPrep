package com.example.examprep.repositories;

import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;

public interface SolvedQuestionRepository extends JpaRepository<SolvedQuestion, Long> {

    @Override
    LinkedList<SolvedQuestion> findAll();

    List<SolvedQuestion> findByUser(User user);

    SolvedQuestion findByUserAndTask_id(User user, Long task);

    @Query("SELECT DISTINCT b FROM SolvedQuestion b where b.user = :#{#user} and b.task.category.id= :#{#id}")
    List<SolvedQuestion> findAllByUserAndCategory(@Param("user")User user,@Param("id") Long id);

    SolvedQuestion findByUserAndTask(User user, Task task);

    List<SolvedQuestion> findByLevel_Id(Long level);
}
