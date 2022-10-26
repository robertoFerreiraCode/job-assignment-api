package dev.robertoferreira.jobassignment.Temp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempRepository extends JpaRepository<Temp, Long> {
    Optional<Temp> findByUsername(String username);

    Boolean existsByUsername(String username);
}
