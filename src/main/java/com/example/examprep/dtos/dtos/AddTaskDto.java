package com.example.examprep.dtos.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Getter
@Setter
public class AddTaskDto {

    private String task;
    private double points;
    private ArrayList<MultipartFile> graphics;

}
