package com.example.examprep.serviceImpls;

import com.example.examprep.models.SolvedQuestion;
import com.example.examprep.models.Task;
import com.example.examprep.models.User;
import com.example.examprep.repositories.TaskRepository;
import com.example.examprep.services.SolvedQuestionService;
import com.example.examprep.services.TaskService;
import com.example.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StandardTaskService implements TaskService {
    private static final double PERCENTAGE_OF_NEW_TASK = 0.5;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SolvedQuestionService solvedQuestionService;

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public LinkedList<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getNextTask(Long category) {
        User user = userService.findCurrentUser();
        List<SolvedQuestion> solvedQuestions = solvedQuestionService.findAllByUserAndCategory(user, category);
        LinkedList<Task> randomTask = findAllByNotUserAndCategory(user, category);

        if ((randomTask.size() > 0) && (Math.random() < PERCENTAGE_OF_NEW_TASK || solvedQuestions.size() == 0)) {
            return randomTask.get(new Random().nextInt(randomTask.size()));
        } else {
            return getMaxPriority(solvedQuestions);
        }

    }

    private LinkedList<Task> findAllByNotUserAndCategory(User user, Long category) {
        return taskRepository.findAllByNotUserAndCategory(user.getId(), category);
    }

    @Override
    public List<Task> findByCategory(Long id) {
        return taskRepository.findByCategory_Id(id);
    }

    @Override
    public List<Task> findByExam(Long id) {
        return taskRepository.findByExam_Id(id);
    }

    private Task getMaxPriority(List<SolvedQuestion> solvedQuestions) {
        SolvedQuestion maxPriority = solvedQuestions.get(0);

        for (SolvedQuestion i : solvedQuestions) {
            double duration = (i.getLevel().getLevelNumber() + 1) * Duration.between(i.getLocalDateTime(), LocalDateTime.now()).toSeconds();
            double oldDuration = (maxPriority.getLevel().getLevelNumber() + 1) * Duration.between(maxPriority.getLocalDateTime(), LocalDateTime.now()).toSeconds();

            if (duration > oldDuration) {
                maxPriority = i;
            }
        }

        return maxPriority.getTask();
    }

}
