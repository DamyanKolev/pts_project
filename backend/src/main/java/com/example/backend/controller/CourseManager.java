package com.example.backend.controller;

import com.example.backend.dto.CourseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CourseManager {

    @GetMapping("/courses-data")
    public ResponseEntity<?> coursesData() {

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/create-course")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseRequest) {

        return new ResponseEntity<>("Test", HttpStatus.OK);
    }

}
