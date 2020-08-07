package com.hctec.candidates.repository;

import com.hctec.candidates.domain.Interview;
import org.springframework.data.repository.CrudRepository;


public interface InterviewRepository extends CrudRepository<Interview, Long> {
}
