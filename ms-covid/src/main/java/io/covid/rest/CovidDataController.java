package io.covid.rest;

import java.util.List;
import java.util.Optional;
import io.covid.db.model.CovidData;
import io.covid.dto.chart.Series;
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
    public ResponseEntity<CovidData> findById(@PathVariable("id") final String id){
        Optional<CovidData> covidData = covidDataService.searchByCovidDataId(id);
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/data/lastweek/{iso}/{date}")
    public ResponseEntity<List<CovidData>> findWeekOldRecords(@PathVariable("iso") final String iso,@PathVariable("date") final String date){
        Optional<List<CovidData>> covidData = covidDataService.findWeekOldRecordsByDateAndIso(iso, date);
        covidDataService.lastYearStatistics(iso);
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/data/isocodes")
    public ResponseEntity<List<String>> isoCodes(){
        Optional<List<String>> covidData = covidDataService.fetchDistinctIsoCode();
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(path = "/data/statistics/{iso}")
    public ResponseEntity<Series> isoCodes(@PathVariable("iso") final String iso){
        Series covidData = covidDataService.lastYearStatistics(iso);
        return new ResponseEntity<Series>(covidData, HttpStatus.OK);
    }
}
