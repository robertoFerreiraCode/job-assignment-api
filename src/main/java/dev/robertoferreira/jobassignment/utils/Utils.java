package dev.robertoferreira.jobassignment.utils;

import dev.robertoferreira.jobassignment.entities.Job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
//    Checks for any date clash for the inputted job against a list of jobs
    public static boolean datesClash(List<Job> jobList, Job job) {
        boolean dateClash = false;
        if (!jobList.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startA = LocalDate.parse(job.getStartDate(), formatter);
            LocalDate endA = LocalDate.parse(job.getEndDate(), formatter);

            dateClash = jobList
                    .parallelStream()
                    .anyMatch(currentJob -> {
                        LocalDate startB = LocalDate.parse(currentJob.getStartDate(), formatter);
                        LocalDate endB = LocalDate.parse(currentJob.getEndDate(), formatter);
                        return (startA.isBefore(endB) && endA.isAfter(startB)
                                || endA.isEqual(startB) || startA.isEqual(endB));
                    });
        }
        return dateClash;
    }
}
