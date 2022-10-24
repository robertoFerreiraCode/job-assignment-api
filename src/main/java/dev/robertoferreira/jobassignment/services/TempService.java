package dev.robertoferreira.jobassignment.services;

import dev.robertoferreira.jobassignment.dtos.TempDTO;
import dev.robertoferreira.jobassignment.dtos.TempIdentifierDTO;
import dev.robertoferreira.jobassignment.entities.Job;
import dev.robertoferreira.jobassignment.entities.Temp;
import dev.robertoferreira.jobassignment.repositories.TempRepository;
import dev.robertoferreira.jobassignment.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TempService {
    @Autowired
    private TempRepository repository;

    public Optional<Temp> getTemp(long id) {
        return this.repository.findById(id);
    }

    public List<Temp> getAllTemps() {
        return this.repository.findAll();
    }

    public List<Temp> filterAvailableTemps(Job job) {
        List<Temp> allTemps = getAllTemps();
        return allTemps
                .stream()
                .filter((temp) -> !Utils.datesClash(temp.getJobs(), job))
                .collect(Collectors.toList());
    }

    public void createTemp(TempDTO data) {
        Temp temp = new Temp(data.getFirstName(), data.getLastName());
        repository.save(temp);
    }

    public Temp assignReports(long id, TempIdentifierDTO data) {
        Optional<Temp> fetchedTemp = this.getTemp(id);
        if (fetchedTemp.isEmpty()) { return null; }
        Temp temp = fetchedTemp.get();

        long tempID = data.getTempID();
        Optional<Temp> fetchedAssignableTemp = this.getTemp(tempID);
        if (fetchedAssignableTemp.isEmpty()) { return null; }
        Temp assignableTemp = fetchedAssignableTemp.get();

        Set<Temp> reportSet = temp.getReports();
//        need to check if  not  assignable is managing temp
        if (!reportSet.contains(assignableTemp) && !assignableTemp.getReports().contains(temp)) {
            reportSet.add(assignableTemp);
            assignableTemp.setManager(temp);
            return temp;
        }
        return null;
    }
}
