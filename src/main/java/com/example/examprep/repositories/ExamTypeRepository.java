package com.example.examprep.repositories;

import com.example.examprep.models.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {
}
