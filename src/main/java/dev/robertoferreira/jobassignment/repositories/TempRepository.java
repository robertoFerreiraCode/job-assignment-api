package dev.robertoferreira.jobassignment.repositories;

import dev.robertoferreira.jobassignment.entities.Temp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<Temp, Long> {

}
