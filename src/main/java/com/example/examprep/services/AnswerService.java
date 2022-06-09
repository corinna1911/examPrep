package com.example.examprep.services;

import com.example.examprep.models.Answer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<Answer> findAll();

    Answer save(Answer answer);

    Optional<Answer> findById(Long id);

    void delete(long id);

    void addAnswer(ArrayList<MultipartFile> file, String answer, long taskId) throws IOException;

    Answer findByTaskAndUser(Long taskId);
}
