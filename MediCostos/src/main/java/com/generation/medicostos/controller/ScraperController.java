package com.generation.medicostos.controller;

import com.generation.medicostos.scraper.DrSimiScraper;
import com.generation.medicostos.scraper.FarmaciasAhumadaScraper;
import com.generation.medicostos.scraper.SalcobrandScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    @Autowired
    private FarmaciasAhumadaScraper farmaciasAhumadaScraper;

    @Autowired
    private SalcobrandScraper salcobrandScraper;

    @Autowired
    private DrSimiScraper drSimiScraper;

    @GetMapping("/run")
    public String runScraper() {
        drSimiScraper.scrapeAndSaveMedications();
        return "Scraper ejecutado con éxito";
    }
}