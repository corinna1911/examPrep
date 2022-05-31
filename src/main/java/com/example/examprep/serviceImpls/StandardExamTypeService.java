package com.example.examprep.serviceImpls;

import com.example.examprep.models.ExamType;
import com.example.examprep.repositories.ExamTypeRepository;
import com.example.examprep.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StandardExamTypeService implements ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Override
    public List<ExamType> findAll() {
        return examTypeRepository.findAll();
    }

}
