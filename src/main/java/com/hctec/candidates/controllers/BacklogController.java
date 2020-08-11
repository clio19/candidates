package com.hctec.candidates.controllers;

import com.hctec.candidates.models.Backlog;
import com.hctec.candidates.models.InterviewTask;
import com.hctec.candidates.services.InterviewService;
import com.hctec.candidates.services.InterviewTaskService;
import com.hctec.candidates.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://localhost:8081")
public class BacklogController {

    @Autowired
    private InterviewTaskService interviewTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody InterviewTask interviewTask,
                                            BindingResult result, @PathVariable String backlog_id) {
        //show delete
        //custom exception

        ResponseEntity<?> erroMap = mapValidationErrorService.MapValidationService(result);
        if (erroMap != null) return erroMap;

        InterviewTask interviewTask1 = interviewTaskService.addInterviewTask(backlog_id, interviewTask);

        return new ResponseEntity<InterviewTask>(interviewTask1, HttpStatus.CREATED);

    }

    @GetMapping("/{backlog_id}")
    public Iterable<InterviewTask> getInterviewBacklog(@PathVariable String backlog_id) {

        return interviewTaskService.findBacklogById(backlog_id);

    }

    @GetMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> getInterviewTask(@PathVariable String backlog_id, @PathVariable String it_id) {
        InterviewTask interviewTask = interviewTaskService.findITByInterviewSequence(backlog_id, it_id);
        return new ResponseEntity<InterviewTask>(interviewTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> updateInterviewTask(@Valid @RequestBody InterviewTask interviewTask, BindingResult result,
                                                 @PathVariable String backlog_id, @PathVariable String it_id
    ) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        InterviewTask updatedTask = interviewTaskService.updateByInterviewSequence(interviewTask, backlog_id, it_id);


        return new ResponseEntity<InterviewTask>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> deleteInterviewTask(@PathVariable String backlog_id, @PathVariable String it_id){
//        Backlog backlog = interviewTask.getBacklog();
//       List <InterviewTask> its = backlog.getInterviewTask();
//       its.remove(interviewTask);
//        backlogRepository.save(backlog)
        interviewTaskService.deleteITByInterviewSequence(backlog_id, it_id);

        return new ResponseEntity<String>("Interview Task "+it_id+" was deleted successfully", HttpStatus.OK);
    }



}
