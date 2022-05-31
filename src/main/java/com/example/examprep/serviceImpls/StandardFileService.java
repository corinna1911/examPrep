package com.example.examprep.serviceImpls;

import com.example.examprep.models.DBFile;
import com.example.examprep.repositories.DBFileRepository;
import com.example.examprep.services.FileService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Service
@Getter
@Setter
public class StandardFileService implements FileService {

    private static final String FOLDER = "C:\\Users\\cspagert\\testOrdner\\";
    @Autowired
    private DBFileRepository fileRepository;

    @Override
    public String saveUploadedFile(MultipartFile file) throws IOException {


        byte[] bytes = file.getBytes();
        Path path = Paths.get(FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        return path.toString();
    }

    public DBFile storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(dbFile);
    }

    @Override
    public LinkedList<DBFile> storeFile(List<MultipartFile> files) throws IOException {
        LinkedList<DBFile> storedFiles = new LinkedList<DBFile>();

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            storedFiles.add(fileRepository.save(dbFile));
        }
        return storedFiles;
    }

    public DBFile getFileById(long fileId) {
        return fileRepository.findById(fileId);
    }

}
