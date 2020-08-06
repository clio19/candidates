package com.hctec.candidates.repository;

import com.hctec.candidates.models.Job;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    // Mines
    Page<Job> findByPublished(boolean published, Pageable pageable);
    Page<Job> findByTitleContaining(String title,  Pageable pageable);
    // Sort list
    List<Job> findByTitleContaining(String title, Sort sort);

}
