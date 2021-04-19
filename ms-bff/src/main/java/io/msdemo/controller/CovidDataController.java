package io.msdemo.controller;

import io.msdemo.feign.CovidDataFeignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidDataController {

    private CovidDataFeignService feignService;

    public CovidDataController(CovidDataFeignService feignService) {
        this.feignService = feignService;
    }
    @GetMapping(path = "/data/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") String id){
        return new ResponseEntity<>(feignService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "data/lastweek/{iso}/{date}")
    public ResponseEntity<String> findWeekOldRecords(@PathVariable("iso") final String iso,@PathVariable("date") final String date){
        return new ResponseEntity<>(feignService.findWeekOldRecords(iso, date), HttpStatus.OK);
    }

    @GetMapping(path = "/data/isocodes")
    public ResponseEntity<String> isoCodes(){
        return new ResponseEntity<>(feignService.isoCodes(), HttpStatus.OK);
    }

    @GetMapping(path = "/data/statistics/{iso}")
    public ResponseEntity<String> lastYearStatistics(@PathVariable("iso") final String iso){
        return new ResponseEntity<>(feignService.lastYearStatistics(iso), HttpStatus.OK);
    }
}
