package com.hctec.candidates.controllers;

import com.hctec.candidates.models.Job;
import com.hctec.candidates.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository jobRepository;


    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(@RequestParam(required = false) String title) {
        try {
            List<Job> jobs = new ArrayList<Job>();

            if (title == null)
                jobRepository.findAll().forEach(jobs::add);
            else
                jobRepository.findByTitleContaining(title).forEach(jobs::add);
            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jobs, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") long id) {
        Optional<Job> jobData = jobRepository.findById(id);

        if (jobData.isPresent()) {
            return new ResponseEntity<>(jobData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        try {
            Job _job = jobRepository
                    .save(new Job(job.getTitle(), job.getDescription(), false));
            return new ResponseEntity<>(_job, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") long id, @RequestBody Job job) {
        Optional<Job> jobData = jobRepository.findById(id);

        if (jobData.isPresent()) {
            Job _job = jobData.get();
            _job.setTitle(job.getTitle());
            _job.setDescription(job.getDescription());
            _job.setPublished(job.isPublished());
            return new ResponseEntity<>(jobRepository.save(_job), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutojobsrials/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") long id) {
        try {
            jobRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/jobs")
    public ResponseEntity<HttpStatus> deleteAllJobs() {
        try {
            jobRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/jobs/published")
    public ResponseEntity<List<Job>> findByPublished() {
        try {
            List<Job> jobs = jobRepository.findByPublished(true);

            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
