package de.likeherotozero.controller;

import de.likeherotozero.entity.Co2Emission;
import de.likeherotozero.entity.Country;
import de.likeherotozero.repository.CountryRepository;
import de.likeherotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    private final CountryRepository countryRepository;
    private final EmissionService emissionService;

    public HomeController(
            CountryRepository countryRepository,
            EmissionService emissionService
    ) {
        this.countryRepository = countryRepository;
        this.emissionService = emissionService;
    }

    @GetMapping("/")
    public String showHomePage(
            @RequestParam(required = false) Long countryId,
            Model model
    ) {
        model.addAttribute("countries", countryRepository.findAll());

        if (countryId != null) {

            Optional<Country> selectedCountry =
                    countryRepository.findById(countryId);

            if (selectedCountry.isPresent()) {

                Country country = selectedCountry.get();

                model.addAttribute("selectedCountry", country);

                Optional<Co2Emission> latestEmission =
                        emissionService.findLatestEmission(country);

                latestEmission.ifPresent(
                        emission -> model.addAttribute(
                                "latestEmission",
                                emission
                        )
                );
            }
        }

        return "index";
    }
}