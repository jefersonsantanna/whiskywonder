package com.jeferson.whiskywonder.service;

import com.jeferson.whiskywonder.model.entity.Whisky;
import com.jeferson.whiskywonder.model.repository.WhiskyRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



    @Service
    public class WhiskyCsvImporter {

        private static final Logger logger = Logger.getLogger(WhiskyCsvImporter.class.getName());
        private final WhiskyRepository whiskyRepository;

        @Autowired
        public WhiskyCsvImporter(WhiskyRepository whiskyRepository) {
            this.whiskyRepository = whiskyRepository;
        }

        public void importFromCsv(String filePath) {
            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] line;
                List<Whisky> whiskies = new ArrayList<>();
                reader.readNext();

                while ((line = reader.readNext()) != null) ;
                {
                    try {
                        Whisky whisky = new Whisky();
                        whisky.setBrand(line[0]);
                        whisky.setType(line[1]);
                        whisky.setYear(Integer.parseInt(line[2]));
                        whisky.setCountry(line[3]);
                        whiskies.add(whisky);
                    } catch (NumberFormatException ex) {
                        logger.warning("Ano inválido no arquivo CSV" + String.join(",", line));

                    }

                }
                whiskyRepository.saveAll(whiskies);
            } catch (IOException | CsvValidationException e) {
                logger.severe("Erro ao ler o arquivo CSV:" + e.getMessage());
            }


        }


    }
























