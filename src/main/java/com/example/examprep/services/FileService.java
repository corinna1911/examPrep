package com.example.examprep.services;

import com.example.examprep.models.DBFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileService {

    String saveUploadedFile(MultipartFile file) throws IOException;

    DBFile storeFile(MultipartFile file) throws IOException;

    List<DBFile> storeFile(List<MultipartFile> files) throws IOException;

    DBFile getFileById(long id);

}
