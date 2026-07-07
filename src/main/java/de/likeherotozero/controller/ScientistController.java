package de.likeherotozero.controller;

import de.likeherotozero.entity.Country;
import de.likeherotozero.repository.CountryRepository;
import de.likeherotozero.service.EmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class ScientistController {

    private final CountryRepository countryRepository;
    private final EmissionService emissionService;

    public ScientistController(
            CountryRepository countryRepository,
            EmissionService emissionService
    ) {
        this.countryRepository = countryRepository;
        this.emissionService = emissionService;
    }

    @GetMapping("/scientist")
    public String showScientistPage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "scientist";
    }

    @PostMapping("/scientist/emissions")
    public String saveEmission(
            @RequestParam Long countryId,
            @RequestParam Integer year,
            @RequestParam BigDecimal co2Kt,
            RedirectAttributes redirectAttributes
    ) {
        Country country = countryRepository
                .findById(countryId)
                .orElseThrow();

        try {
            emissionService.saveEmission(
                    country,
                    year,
                    co2Kt
            );

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "CO₂-Datensatz wurde erfolgreich gespeichert."
            );

        } catch (IllegalArgumentException exception) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    exception.getMessage()
            );
        }

        return "redirect:/scientist";
    }
}