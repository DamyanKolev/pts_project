package com.example.backend.service;

import com.example.backend.repository.FileRepository;

public class DBAccess {
    private FileRepository fileRepository;

    public String getFileByFilename(String fname){
        return fileRepository.findByFilename(fname).filePath;
    }
}
