package com.hctec.candidates.controllers;


import com.hctec.candidates.execption.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.hctec.candidates.models.Job;
import com.hctec.candidates.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/sortedjobs")
    public ResponseEntity<List<Job>> getAllJobs(@RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 columns
                for (String sortOrder : sort) {
                    // sortOrder="column, direction"
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[column, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Job> jobs = jobRepository.findAll(Sort.by(orders));

            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<Map<String, Object>> getAllJobs(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Job> jobs = new ArrayList<Job>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Job> pageTuts;
            if (title == null)
                pageTuts = jobRepository.findAll(pagingSort);
            else
                pageTuts = jobRepository.findByTitleContaining(title, pagingSort);

            jobs = pageTuts.getContent();

            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("jobs", jobs);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/jobs")
//    // public ResponseEntity<List<Job>> getAllJobs(@RequestParam(required = false) String title) {
//    public ResponseEntity<Map<String, Object>> getAllJobs(
//            @RequestParam(required = false) String title,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size
//    ) {
//        try {
//            List<Job> jobs = new ArrayList<Job>();
//            Pageable paging = PageRequest.of(page, size);
//
//            Page<Job> pageJobs;
//
//            if (title == null)
//                //jobRepository.findAll().forEach(jobs::add);
//                pageJobs = jobRepository.findAll(paging);
//            else
//               // jobRepository.findByTitleContaining(title).forEach(jobs::add);
//                pageJobs = jobRepository.findByTitleContaining(title, paging);
//
//            jobs = pageJobs.getContent();
//            if (jobs.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//           // return new ResponseEntity<>(jobs, HttpStatus.OK);
//            Map<String, Object> response = new HashMap<>();
//            response.put("jobs", jobs);
//            response.put("currentPage", pageJobs.getNumber());
//            response.put("totalItems", pageJobs.getTotalElements());
//            response.put("totalPages", pageJobs.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/jobs")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
        Job _job = jobRepository.save(new Job(job.getTitle(), job.getDescription(), false));
        return new ResponseEntity<>(_job, HttpStatus.CREATED);
//        try {
//            Job _job = jobRepository
//                    .save(new Job(job.getTitle(), job.getDescription(), false));
//            return new ResponseEntity<>(_job, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") long id) {
        Optional<Job> jobData = Optional.ofNullable(jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Job with id = " + id)));

        return new ResponseEntity<>(jobData.get(), HttpStatus.OK);

//        if (jobData.isPresent()) {
//            return new ResponseEntity<>(jobData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") long id, @Valid @RequestBody Job job) {
        Optional<Job> jobData = Optional.ofNullable(jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id)));

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

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") long id) {
        try {
            jobRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
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
    // public ResponseEntity<List<Job>> findByPublished() {
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            //  List<Job> jobs = jobRepository.findByPublished(true);
            List<Job> jobs = new ArrayList<Job>();
            Pageable paging = PageRequest.of(page, size);

            Page<Job> pageJobs = jobRepository.findByPublished(true, paging);
            jobs = pageJobs.getContent();

            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // return new ResponseEntity<>(jobs, HttpStatus.OK);
            Map<String, Object> response = new HashMap<>();

            response.put("jobs", jobs);
            response.put("currentPage", pageJobs.getNumber());
            response.put("totalItems", pageJobs.getTotalElements());
            response.put("totalPages", pageJobs.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
