package com.example.examprep.repositories;

import com.example.examprep.models.DBFile;
import com.example.examprep.models.LevelOfDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {

    DBFile findById(long id);

}
