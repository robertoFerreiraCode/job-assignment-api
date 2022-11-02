package dev.robertoferreira.jobassignment.Job;

import dev.robertoferreira.jobassignment.Temp.TempIdentifierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping(value = "/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

//    GET /jobs - Fetch all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobList = this.jobService.getAllJobs();
        if (jobList.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

//    GET /jobs/{id} - (id, name, tempId, startDate, endDate)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Job> getJob(@PathVariable long id) {
        Optional<Job> job = this.jobService.getJob(id);
        if (job.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(job.get(), HttpStatus.OK);
    }
//    GET /jobs?assigned={true|false} - Filter by whether a job is assigned to a temp or not
    @GetMapping(params = "assigned")
    public ResponseEntity<List<Job>> filterAssignedJobs(@RequestParam Boolean assigned) {
        List<Job> jobList = this.jobService.filterAssignedJobs(assigned);
        if (jobList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }
//    POST /temps - Create a temp
    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobDTO data) {
        return new ResponseEntity<>(jobService.createJob(data), HttpStatus.CREATED);
    }

//    PATCH /jobs/{id} - Updates job, endpoint used to assign temps to jobs
    @PatchMapping(value = "/{jobID}")
    public ResponseEntity<Job> assignTemp(@PathVariable long jobID, @Valid @RequestBody TempIdentifierDTO data) {
        Job job = this.jobService.assignTemp(jobID, data);
        if (job == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}
