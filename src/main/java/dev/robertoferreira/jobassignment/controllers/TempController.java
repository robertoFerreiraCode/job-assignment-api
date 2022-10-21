package dev.robertoferreira.jobassignment.controllers;

import dev.robertoferreira.jobassignment.dtos.TempDTO;
import dev.robertoferreira.jobassignment.entities.Job;
import dev.robertoferreira.jobassignment.entities.Temp;
import dev.robertoferreira.jobassignment.services.JobService;
import dev.robertoferreira.jobassignment.services.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/temps")
public class TempController {
    @Autowired
    private TempService tempService;
    @Autowired
    private JobService jobService;

//    GET /temps - List all temps
    @GetMapping
    public ResponseEntity<List<Temp>> getAllTemps() {
        List<Temp> tempList = this.tempService.getAllTemps();
        if (tempList.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    // eg http://localhost:8080/temps/8
    @GetMapping(value = "/{id}")
    public ResponseEntity<Temp> getTemp(@PathVariable long id) {

        Optional<Temp> temp = this.tempService.getTemp(id);
        if (temp.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp.get(), HttpStatus.OK);
    }
//    GET /temps?jobId={jobId} - List temps available for a job based on the jobs date range
    @GetMapping(params = "jobID")
    public ResponseEntity<List<Temp>> filterAvailableTemps(@RequestParam long jobID) {
        Optional<Job> job = this.jobService.getJob(jobID);
        if (job.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        List<Temp> tempList = this.tempService.filterAvailableTemps(job.get());
        if (tempList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

//    GET /temps/{id} - get temp by id
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTemp(@Valid @RequestBody TempDTO temp) {
        tempService.createTemp(temp);
    }
}
