package dev.robertoferreira.jobassignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "temps")
public class Temp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @OneToMany
    private List<Job> jobs = new ArrayList<>();
    @OneToMany(mappedBy="manager")
    private Set<Temp> reports;
    @ManyToOne
    private Temp manager;

    public Temp(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Temp() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnoreProperties("temp")
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @JsonIgnoreProperties({"reports", "manager", "jobs"})
    public Set<Temp> getReports() { return reports; }

    public void setReports(Set<Temp> reports) { this.reports = reports; }

    @JsonIgnoreProperties({"reports", "manager", "jobs"})
    public Temp getManager() { return manager; }

    public void setManager(Temp manager) { this.manager = manager; }
}
