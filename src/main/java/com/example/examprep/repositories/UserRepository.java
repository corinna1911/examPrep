package com.example.examprep.repositories;

import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    LinkedList<User> findAll();

    User save(User user);

    User findByName(String username);
}
