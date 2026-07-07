package de.likeherotozero.service;

import de.likeherotozero.entity.Co2Emission;
import de.likeherotozero.entity.Country;
import de.likeherotozero.repository.Co2EmissionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class EmissionService {

    private final Co2EmissionRepository co2EmissionRepository;

    public EmissionService(
            Co2EmissionRepository co2EmissionRepository
    ) {
        this.co2EmissionRepository = co2EmissionRepository;
    }

    public Optional<Co2Emission> findLatestEmission(
            Country country
    ) {
        return co2EmissionRepository
                .findTopByCountryOrderByYearDesc(country);
    }

    public Co2Emission saveEmission(
            Country country,
            Integer year,
            BigDecimal co2Kt
    ) {
        boolean alreadyExists =
                co2EmissionRepository.existsByCountryAndYear(
                        country,
                        year
                );

        if (alreadyExists) {
            throw new IllegalArgumentException(
                    "Für " + country.getName()
                            + " und das Jahr " + year
                            + " existiert bereits ein Datensatz."
            );
        }

        Co2Emission emission = new Co2Emission(
                country,
                year,
                co2Kt
        );

        return co2EmissionRepository.save(emission);
    }
}