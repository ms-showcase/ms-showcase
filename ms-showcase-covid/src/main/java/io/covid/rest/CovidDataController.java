package io.covid.rest;

import java.util.Optional;
import io.covid.db.model.CovidData;
import io.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidDataController {

    private CovidDataService covidDataService;

    @Autowired
    public CovidDataController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping(path = "/data/{id}")
    public ResponseEntity<CovidData> find(@PathVariable("id") final String id){
        Optional<CovidData> covidData = covidDataService.searchByCovidDataId(id);
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
