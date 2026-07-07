package de.likeherotozero.repository;

import de.likeherotozero.entity.Co2Emission;
import de.likeherotozero.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Co2EmissionRepository
        extends JpaRepository<Co2Emission, Long> {

    Optional<Co2Emission>
    findTopByCountryOrderByYearDesc(Country country);

    boolean existsByCountryAndYear(
            Country country,
            Integer year
    );
}