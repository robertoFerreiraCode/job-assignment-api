package dev.robertoferreira.jobassignment.repositories;

import dev.robertoferreira.jobassignment.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query (nativeQuery = true, value = "SELECT * FROM jobs WHERE temp_id IS NOT null")
    List<Job> getAssignedJobs();

    @Query (nativeQuery = true, value = "SELECT * FROM jobs WHERE temp_id IS null")
    List<Job> getUnAssignedJobs();
}
