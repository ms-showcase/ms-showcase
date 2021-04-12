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
    public ResponseEntity<String> find(@PathVariable("id") String id){
        return new ResponseEntity<>(feignService.getCovidData(id), HttpStatus.OK);
    }
}
