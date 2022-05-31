package com.example.examprep.serviceImpls;

import com.example.examprep.models.*;
import com.example.examprep.repositories.AnswerRepository;
import com.example.examprep.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

@Service
public class StandardAnswerService implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private SolvedQuestionService solvedQuestionService;

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public LinkedList<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public void delete(long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public void addAnswer(ArrayList<MultipartFile> file, String answerText, long taskId) throws IOException {
        DBFile answerFile = file == null ? null : fileService.storeFile(file.get(0));
        Task task = taskService.findById(taskId).get();
        User user = userService.findCurrentUser();
        SolvedQuestion solvedQuestion = solvedQuestionService.findByUserAndTask(user, task);
        Answer answer = new Answer(answerText, answerFile, task);

        if (solvedQuestion != null) {
            solvedQuestion.setLocalDateTime(LocalDateTime.now());
            solvedQuestion.setAnswer(answer);
            solvedQuestion.setTask(task);
            solvedQuestionService.save(solvedQuestion);
        } else {
            solvedQuestionService.save(new SolvedQuestion(task, answer, user));
        }

    }

    @Override
    public Answer findByTaskAndUser(Long task) {
        SolvedQuestion solvedQuestion = solvedQuestionService.findByUserAndTaskId(userService.findCurrentUser(), task);
        return solvedQuestion == null ? null : solvedQuestion.getAnswer();
    }

}
