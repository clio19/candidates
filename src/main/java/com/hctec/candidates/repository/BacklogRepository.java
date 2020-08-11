package com.hctec.candidates.repository;

import com.hctec.candidates.models.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    Backlog findByInterviewIdentifier(String identifier);
}
