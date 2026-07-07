package de.likeherotozero.config;

import de.likeherotozero.entity.Co2Emission;
import de.likeherotozero.entity.Country;
import de.likeherotozero.repository.Co2EmissionRepository;
import de.likeherotozero.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

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
    public void run(String... args) throws Exception {

        createCountriesIfNecessary();

        if (co2EmissionRepository.count() == 0) {
            importCo2DataFromCsv();
        }
    }

    private void createCountriesIfNecessary() {

        if (countryRepository.count() == 0) {
            countryRepository.save(
                    new Country("Deutschland", "DEU")
            );

            countryRepository.save(
                    new Country("Frankreich", "FRA")
            );

            countryRepository.save(
                    new Country("Italien", "ITA")
            );
        }
    }

    private void importCo2DataFromCsv() throws Exception {

        ClassPathResource resource =
                new ClassPathResource(
                        "data/co2-emissions-sample.csv"
                );

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                resource.getInputStream(),
                                StandardCharsets.UTF_8
                        )
                )
        ) {
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String cleanedLine =
                        line.replace("\"", "");

                String[] values =
                        cleanedLine.split(",");

                String countryCode = values[0];
                Integer year = Integer.valueOf(values[1]);
                BigDecimal co2Kt =
                        new BigDecimal(values[2]);

                Country country = countryRepository
                        .findByCountryCode(countryCode)
                        .orElseThrow(
                                () -> new IllegalStateException(
                                        "Kein Land für ISO-Code gefunden: "
                                                + countryCode
                                )
                        );

                Co2Emission emission =
                        new Co2Emission(
                                country,
                                year,
                                co2Kt
                        );

                co2EmissionRepository.save(emission);
            }
        }
    }
}