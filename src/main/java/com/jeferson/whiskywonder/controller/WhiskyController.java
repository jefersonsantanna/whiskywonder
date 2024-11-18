package com.jeferson.whiskywonder.controller;


import com.jeferson.whiskywonder.model.entity.Whisky;
import com.jeferson.whiskywonder.service.WhiskyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    private final WhiskyService whiskyService;

    public WhiskyController(WhiskyService whiskyService) {
        this.whiskyService = whiskyService;

    }

    @GetMapping("/whiskies")
    public List<Whisky> searchWhiskies(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String country) {

        return whiskyService.searchWhiskies(brand, type, minYear, maxYear, country);


    }
}