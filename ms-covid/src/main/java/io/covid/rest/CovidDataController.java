package io.covid.rest;

import java.util.List;
import java.util.Optional;
import io.covid.db.model.CovidData;
import io.covid.dto.chart.Series;
import io.covid.service.CovidDataService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get CovidData by its id") @GetMapping(path = "/data/{id}")
    public ResponseEntity<CovidData> findById(
        @ApiParam(name = "ID of CovidData") @PathVariable("id") final String id) {
        Optional<CovidData> covidData = covidDataService.searchByCovidDataId(id);
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Get last week CovidData for a specified country")
    @GetMapping(path = "/data/lastweek/{iso}/{date}")
    public ResponseEntity<List<CovidData>> findWeekOldRecords(
        @ApiParam(name = "ISO code of a country")
        @PathVariable("iso") final String iso,
        @ApiParam(name = "Upper bound of the date range") @PathVariable("date") final String date) {
        Optional<List<CovidData>> covidData =
            covidDataService.findWeekOldRecordsByDateAndIso(iso, date);
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Get all available ISO codes")
    @GetMapping(path = "/data/isocodes") public ResponseEntity<List<String>> isoCodes() {
        Optional<List<String>> covidData = covidDataService.fetchDistinctIsoCode();
        return covidData.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Get last year CovidData for a specified country")
    @GetMapping(path = "/data/statistics/{iso}") public ResponseEntity<Series> isoCodes(
        @ApiParam(name = "ISO code of a country")
        @PathVariable("iso") final String iso) {
        Series covidData = covidDataService.lastYearStatistics(iso);
        return new ResponseEntity<>(covidData, HttpStatus.OK);
    }
}
