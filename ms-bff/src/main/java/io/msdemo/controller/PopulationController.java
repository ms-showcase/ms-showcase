package io.msdemo.controller;

import io.msdemo.feign.PopulationFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationController {

    private PopulationFeignService populationFeignService;

    @Autowired
    public PopulationController(PopulationFeignService populationFeignService) {
        this.populationFeignService = populationFeignService;
    }

    @GetMapping(path = "/population/{iso}")
    public ResponseEntity<String> population(@PathVariable("iso") final String iso){
        String population = populationFeignService.population(iso);
        return new ResponseEntity<>(population, HttpStatus.OK);
    }
}

