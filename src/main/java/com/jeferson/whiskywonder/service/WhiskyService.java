package com.jeferson.whiskywonder.service;


import com.jeferson.whiskywonder.exception.ResourceNotFoundException;
import com.jeferson.whiskywonder.model.entity.Whisky;
import com.jeferson.whiskywonder.model.repository.WhiskyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhiskyService {

    private final WhiskyRepository whiskyRepository;

    public WhiskyService(WhiskyRepository whiskyRepository) {
        this.whiskyRepository = whiskyRepository;
    }

    public List<Whisky> searchWhiskies(String brand, String type, Integer minYear, Integer maxYear, String country) {
        if (minYear != null && maxYear != null && minYear > maxYear) {
            throw new IllegalArgumentException("minYear cannot be greater than maxYear");
        }

        List<Whisky> whiskies  = whiskyRepository.findByFilters(brand, type, minYear, maxYear, country);

        if (whiskies.isEmpty()) {
            throw new ResourceNotFoundException("No whiskies found");

        }

        return whiskies;
    }


}
