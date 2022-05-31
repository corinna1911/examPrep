package com.example.examprep.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 100000)
    private String task;
    private String title;
    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "id")
    private Category category;
    @ManyToOne()
    private Exam exam;
    private double points;
    @OneToOne()
    @JoinColumn(name = "solution_id", referencedColumnName = "id")
    private Solution solution;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subtask_id", referencedColumnName = "id")
    private List<Task> subtasks;
    @OneToOne()
    private DBFile file;
    @ManyToOne
    private DBFile aid;
    @ManyToOne
    private ExerciseType exerciseType;

    public Task(String task, double points) {
        this.task = task;
        this.points = points;
    }

    public Task(String title, String task, double points) {
        this.task = task;
        this.points = points;
        this.title = title;
    }

    public Task(String task, double points, DBFile file, Solution solution) {
        this.task = task;
        this.points = points;
        this.file = file;
        this.solution = solution;

    }

    public Task(String task) {
        this.id = Long.parseLong(task);
    }

}
