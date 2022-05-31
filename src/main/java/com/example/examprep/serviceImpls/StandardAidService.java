package com.example.examprep.serviceImpls;

import com.example.examprep.models.Aid;
import com.example.examprep.repositories.AidRepository;
import com.example.examprep.services.AidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardAidService implements AidService {

    @Autowired
    private AidRepository aidRepository;


    @Override
    public Aid save(Aid aid) {
        return aidRepository.save(aid);
    }
}
