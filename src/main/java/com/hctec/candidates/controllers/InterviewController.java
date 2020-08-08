package com.hctec.candidates.controllers;

import com.hctec.candidates.models.Interview;
import com.hctec.candidates.services.InterviewService;
import com.hctec.candidates.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;



    @PostMapping("")
    public ResponseEntity<?> createNewInterview(@Valid @RequestBody Interview interview, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

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

    @GetMapping("/{interviewId}")
    public ResponseEntity<?> getInterviewById(@PathVariable String interviewId){

        Interview interview = interviewService.findInterviewByIdentifier(interviewId);

        return new ResponseEntity<Interview>(interview, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Interview> getAllInterviews(){return interviewService.findAllInterviews();}

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<?> deleteInterview(@PathVariable String interviewId){
        interviewService.deleteInterviewByIdentifier(interviewId);

        return new ResponseEntity<String>("Interview with ID: '"+interviewId+"' was deleted", HttpStatus.OK);
    }
}