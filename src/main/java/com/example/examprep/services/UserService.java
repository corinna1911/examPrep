package com.example.examprep.services;

import com.example.examprep.models.User;
import com.example.examprep.models.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    void save(User user);

    Optional<User> findById(Long id);

    void delete(long id);

    User findByUsername(String username);

    User registerUser(String name, String mail, String password);

    User confirmUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token);

    User findCurrentUser();


}
