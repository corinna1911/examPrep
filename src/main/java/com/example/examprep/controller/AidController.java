package com.example.examprep.controller;

import com.example.examprep.models.Aid;
import com.example.examprep.models.DBFile;
import com.example.examprep.services.AidService;
import com.example.examprep.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/aid")
public class AidController {

    @Autowired
    FileService fileService;
    @Autowired
    AidService aidService;

    @PostMapping(path = "/add")
    public void addAid(@RequestParam ArrayList<MultipartFile> file) throws IOException {
        List<DBFile> taskFile = fileService.storeFile(file);
        aidService.save(new Aid(taskFile));

    }

}
