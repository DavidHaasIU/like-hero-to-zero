package de.likeherotozero.config;

import de.likeherotozero.entity.Co2Emission;
import de.likeherotozero.entity.Country;
import de.likeherotozero.repository.Co2EmissionRepository;
import de.likeherotozero.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final Co2EmissionRepository co2EmissionRepository;

    public DataInitializer(
            CountryRepository countryRepository,
            Co2EmissionRepository co2EmissionRepository
    ) {
        this.countryRepository = countryRepository;
        this.co2EmissionRepository = co2EmissionRepository;
    }

    @Override
    public void run(String... args) {

        if (countryRepository.count() == 0) {
            countryRepository.save(new Country("Deutschland", "DEU"));
            countryRepository.save(new Country("Frankreich", "FRA"));
            countryRepository.save(new Country("Italien", "ITA"));
        }

        if (co2EmissionRepository.count() == 0) {

            Country germany = countryRepository
                    .findAll()
                    .stream()
                    .filter(country -> country.getCountryCode().equals("DEU"))
                    .findFirst()
                    .orElseThrow();

            Country france = countryRepository
                    .findAll()
                    .stream()
                    .filter(country -> country.getCountryCode().equals("FRA"))
                    .findFirst()
                    .orElseThrow();

            Country italy = countryRepository
                    .findAll()
                    .stream()
                    .filter(country -> country.getCountryCode().equals("ITA"))
                    .findFirst()
                    .orElseThrow();

            co2EmissionRepository.save(
                    new Co2Emission(
                            germany,
                            2021,
                            new BigDecimal("675000.00")
                    )
            );

            co2EmissionRepository.save(
                    new Co2Emission(
                            germany,
                            2022,
                            new BigDecimal("666000.00")
                    )
            );

            co2EmissionRepository.save(
                    new Co2Emission(
                            france,
                            2022,
                            new BigDecimal("305000.00")
                    )
            );

            co2EmissionRepository.save(
                    new Co2Emission(
                            italy,
                            2022,
                            new BigDecimal("318000.00")
                    )
            );
        }
    }
}