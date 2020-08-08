package com.hctec.candidates.repository;

import com.hctec.candidates.models.Interview;
import org.springframework.data.repository.CrudRepository;


public interface InterviewRepository extends CrudRepository<Interview, Long> {
    Interview findByInterviewIdentifier(String projectId);

    @Override
    Iterable<Interview> findAll();
}
