package com.example.examprep.serviceImpls;

import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import com.example.examprep.repositories.SolvedQuestionRepository;
import com.example.examprep.services.SolvedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StandardSolvedQuestionService implements SolvedQuestionService {

    @Autowired
    private SolvedQuestionRepository solvedQuestionRepository;

    @Override
    public Optional<SolvedQuestion> findById(Long id) {
        return solvedQuestionRepository.findById(id);
    }

    @Override
    public void save(SolvedQuestion solvedQuestion) {
        if (solvedQuestion.getLocalDateTime() == null) {
            solvedQuestion.setLocalDateTime(LocalDateTime.now());
        }
        solvedQuestionRepository.save(solvedQuestion);
    }


    @Override
    public LinkedList<SolvedQuestion> findAll() {
        return solvedQuestionRepository.findAll();
    }

    @Override
    public void delete(long id) {
        solvedQuestionRepository.deleteById(id);
    }

    @Override
    public List<SolvedQuestion> findAllByUser(User user) {
        return solvedQuestionRepository.findByUser(user);
    }

    @Override
    public SolvedQuestion findByUserAndTask(User user, Task task) {
        return solvedQuestionRepository.findByUserAndTask(user, task);
    }

    @Override
    public SolvedQuestion findByUserAndTaskId(User user, Long task) {
        return solvedQuestionRepository.findByUserAndTask_id(user, task);
    }

    @Override
    public List<SolvedQuestion> findAllByUserAndCategory(User user, Long category) {
        return solvedQuestionRepository.findAllByUserAndCategory(user, category);
    }

    @Override
    public List<SolvedQuestion> findByDifficulty(Long difficulty) {
        return solvedQuestionRepository.findByLevel_Id(difficulty);
    }


}
