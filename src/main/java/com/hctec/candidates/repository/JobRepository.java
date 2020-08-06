package com.hctec.candidates.repository;

import com.hctec.candidates.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    // Mines
    List<Job> findByPublished(boolean published);
    List<Job> findByTitleContaining(String title);
}
