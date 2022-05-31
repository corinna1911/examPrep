package com.example.examprep.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /*@OneToMany(mappedBy = "exam",cascade=CascadeType.PERSIST)
    private List<Task> tasks;*/
    private LocalDate date;
    @ManyToOne
    private ExamType examType;

    public Exam(LocalDate date, ExamType examType){
        this.date = date;
        this.examType = examType;
    }

}