package dev.robertoferreira.jobassignment.services;

import dev.robertoferreira.jobassignment.dtos.JobDTO;
import dev.robertoferreira.jobassignment.dtos.TempIdentifierDTO;
import dev.robertoferreira.jobassignment.entities.Job;
import dev.robertoferreira.jobassignment.entities.Temp;
import dev.robertoferreira.jobassignment.repositories.JobRepository;
import dev.robertoferreira.jobassignment.repositories.TempRepository;
import dev.robertoferreira.jobassignment.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository repository;
    @Autowired
    private TempService tempService;
    @Autowired
    private TempRepository tempRepository;

    public List<Job> getAllJobs() {
        return this.repository.findAll();
    }

    public Optional<Job> getJob(long id) {
        return this.repository.findById(id);
    }

    public List<Job> filterAssignedJobs(Boolean assigned) {
        return assigned ? this.repository.getAssignedJobs() : this.repository.getUnAssignedJobs();
    }

    public Job createJob(JobDTO data) {
        Job job = new Job(data.getName(), data.getStartDate(), data.getEndDate());
        if (data.getTempID() == 0) {
            return repository.save(job);
        }
        long tempID = data.getTempID();
        Optional<Temp> fetchedTemp = tempService.getTemp(tempID);
        if (fetchedTemp.isEmpty()) { return null; }
        Temp temp = fetchedTemp.get();
        job.setTemp(temp);
        return repository.save(job);
    }

    public Job assignTemp(long jobID, TempIdentifierDTO data) {
        Optional<Job> fetchedJob = this.getJob(jobID);
        if (fetchedJob.isEmpty()) { return null; }
        Job job = fetchedJob.get();

        long tempID = data.getTempID();
        Optional<Temp> fetchedTemp = tempService.getTemp(tempID);
        if (fetchedTemp.isEmpty()) { return null; }
        Temp temp = fetchedTemp.get();

        boolean dateClash = Utils.datesClash(temp.getJobs(), job);

        if (job.getTemp() == null && !dateClash) {
            job.setTemp(temp);
            temp.getJobs().add(job);
            return job;
        }
        return null;
    }
}
