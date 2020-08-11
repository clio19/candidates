package com.hctec.candidates.repository;

import com.hctec.candidates.models.InterviewTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InterviewTaskRepository extends JpaRepository<InterviewTask, Long> {
    Iterable<InterviewTask> findByInterviewIdentifierOrderByPriority(String id);

    InterviewTask findByInterviewSequence(String it_id);
}
