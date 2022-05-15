package com.example.backend.repository;


import java.util.Optional;

import com.example.backend.model.CourseFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<CourseFile, Long> {
    
    public Optional<CourseFile> findById(Long id);
    public CourseFile findByFilename(String fname);
}
