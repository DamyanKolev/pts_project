package com.example.backend.controller;

import java.util.List;

import com.example.backend.service.DBAccess;
import com.example.backend.service.FunctionService;
import com.example.backend.dto.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/options")
public class FunctionManager {

    private FunctionService functionService = new FunctionService();

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/CA")
    public ResponseEntity<CADataDTO> correlationAnalysis() {
        CADataDTO data = functionService.CorrelationAnalysis();

        return new ResponseEntity<CADataDTO>(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/DM")
    public ResponseEntity<DMDataDTO> DistractionMeasures() {
        DMDataDTO data = functionService.DistractionMeasures();

        return new ResponseEntity<DMDataDTO>(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/FD")
    public ResponseEntity<List<FDDataDTO>> FrequencyDistribution() {
        List<FDDataDTO> data = functionService.FrequencyDistribution();

        return new ResponseEntity<List<FDDataDTO>>(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/MCT")
    public ResponseEntity<MCTDataDTO> MeasuresOfTheCentralTrend() {
        MCTDataDTO data = functionService.MeasuresOfTheCentralTrend();

        return new ResponseEntity<MCTDataDTO>(data, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/RAS")
    public void readingAndSummarizing() {

    }
}
