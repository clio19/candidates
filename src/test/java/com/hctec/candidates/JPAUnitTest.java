package com.hctec.candidates;

import com.hctec.candidates.models.Job;
import com.hctec.candidates.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JobRepository repository;

    @Test
    public void should_find_no_jobs_if_repository_is_empty() {
        Iterable<Job> jobs = repository.findAll();

        assertThat(jobs).isEmpty();
    }

    @Test
    public void should_store_a_job() {
        Job job = repository.save(new Job("Job title", "Job desc", true));

        assertThat(job).hasFieldOrPropertyWithValue("title", "Job title");
        assertThat(job).hasFieldOrPropertyWithValue("description", "Job desc");
        assertThat(job).hasFieldOrPropertyWithValue("published", true);
    }

    @Test
    public void should_find_all_jobs() {
        Job job1 = new Job("jobt#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job job3 = new Job("job#3", "Desc#3", true);
        entityManager.persist(job3);

        Iterable<Job> jobs = repository.findAll();

        assertThat(jobs).hasSize(3).contains(job1, job2, job3);
    }

    @Test
    public void should_find_job_by_id() {
        Job job1 = new Job("Job#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("Job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job foundJob = repository.findById(job2.getId()).get();

        assertThat(foundJob).isEqualTo(job2);
    }

    @Test
    public void should_find_published_jobs() {
        Job job1 = new Job("Job#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("Job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job job3 = new Job("Job#3", "Desc#3", true);
        entityManager.persist(job3);

        Pageable paging = PageRequest.of(0, 3);

        //Iterable<Job> jobs = repository.findByPublished(true);
        Iterable<Job> jobs = repository.findByPublished(true,paging);

        assertThat(jobs).hasSize(2).contains(job1, job3);
    }

    @Test
    public void should_find_jobs_by_title_containing_string() {
        Job job1 = new Job("Spring Boot Job#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("Java Job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job job3 = new Job("Spring Data JPA Job#3", "Desc#3", true);
        entityManager.persist(job3);

        Pageable paging = PageRequest.of(0, 3);

        Iterable<Job> jobs = repository.findByTitleContaining("ring", paging);

        assertThat(jobs).hasSize(2).contains(job1, job3);
    }

    @Test
    public void should_update_job_by_id() {
        Job job1 = new Job("Job#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("Job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job updatedJob = new Job("updated Job#2", "updated Desc#2", true);

        Job job = repository.findById(job2.getId()).get();
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setPublished(updatedJob.isPublished());
        repository.save(job);

        Job checkJob = repository.findById(job2.getId()).get();

        assertThat(checkJob.getId()).isEqualTo(job2.getId());
        assertThat(checkJob.getTitle()).isEqualTo(updatedJob.getTitle());
        assertThat(checkJob.getDescription()).isEqualTo(updatedJob.getDescription());
        assertThat(checkJob.isPublished()).isEqualTo(updatedJob.isPublished());
    }

    @Test
    public void should_delete_job_by_id() {
        Job job1 = new Job("Job#1", "Desc#1", true);
        entityManager.persist(job1);

        Job job2 = new Job("Job#2", "Desc#2", false);
        entityManager.persist(job2);

        Job job3 = new Job("Job#3", "Desc#3", true);
        entityManager.persist(job3);

        repository.deleteById(job2.getId());

        Iterable<Job> jobs = repository.findAll();

        assertThat(jobs).hasSize(2).contains(job1, job3);
    }

    @Test
    public void should_delete_all_jobs() {
        entityManager.persist(new Job("Job#1", "Desc#1", true));
        entityManager.persist(new Job("Job#2", "Desc#2", false));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}
