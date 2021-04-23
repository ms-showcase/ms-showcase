package io.msdemo.controller;

import io.msdemo.dto.PopulationDto;
import io.msdemo.feign.PopulationFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController public class PopulationController {

    private PopulationFeignService populationFeignService;

    @Autowired public PopulationController(PopulationFeignService populationFeignService) {
        this.populationFeignService = populationFeignService;
    }

    @GetMapping(path = "/population/{iso}")
    public ResponseEntity<PopulationDto> population(@PathVariable("iso") final String iso) {
        return new ResponseEntity<>(populationFeignService.population(iso), HttpStatus.OK);
    }
}

