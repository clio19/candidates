package com.hctec.candidates.controllers;

import com.hctec.candidates.domain.Interview;
import com.hctec.candidates.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;



    @PostMapping("")
    public ResponseEntity<?> createNewInterview( @RequestBody Interview interview){

//        if(result.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//
////            for(FieldError error: result.getFieldErrors()){
////                errorMap.put(error.getField(), error.getDefaultMessage());
////            }
//            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
//        }
//


        Interview interview1 = interviewService.saveOrUpdateInterview(interview);
        return new ResponseEntity<Interview>(interview1, HttpStatus.CREATED);
    }
}