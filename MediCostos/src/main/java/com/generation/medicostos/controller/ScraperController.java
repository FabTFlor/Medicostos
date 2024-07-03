package com.generation.medicostos.controller;

import com.generation.medicostos.scraper.FarmaciasAhumadaScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    @Autowired
    private FarmaciasAhumadaScraper farmaciasAhumadaScraper;

    @GetMapping("/run")
    public String runScraper() {
        farmaciasAhumadaScraper.scrapeAndSaveMedications();
        return "Scraper ejecutado con éxito";
    }
}