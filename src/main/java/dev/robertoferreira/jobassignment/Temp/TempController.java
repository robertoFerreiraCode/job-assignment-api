package dev.robertoferreira.jobassignment.Temp;

import dev.robertoferreira.jobassignment.Job.Job;
import dev.robertoferreira.jobassignment.Job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
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
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    // GET /temps/{id} - get temp by id
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
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

//    POST /temps - Create a temp
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createTemp(@Valid @RequestBody TempDTO temp) {
        tempService.createTemp(temp);
    }

    // PATCH /temps/{id} - Add temps to be managed
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Temp> assignReports(@PathVariable long id, @Valid @RequestBody TempIdentifierDTO data) {
        Temp temp = this.tempService.assignReports(id, data);
        if (temp == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
}
