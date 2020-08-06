package com.hctec.candidates.repository;

import com.hctec.candidates.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepository extends JpaRepository<Job, Long> {
    // Mines
    Page<Job> findByPublished(boolean published, Pageable pageable);
    Page<Job> findByTitleContaining(String title,  Pageable pageable);
}
