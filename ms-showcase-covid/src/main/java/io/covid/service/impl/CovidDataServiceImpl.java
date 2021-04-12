package io.covid.service.impl;

import java.util.Optional;
import io.covid.db.model.CovidData;
import io.covid.db.repository.CovidDataRepository;
import io.covid.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CovidDataServiceImpl implements CovidDataService {

    private CovidDataRepository covidDataRepository;

    @Autowired
    public CovidDataServiceImpl(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }


    @Override
    public Optional<CovidData> searchByCovidDataId(final String id) {
        return covidDataRepository.findById(id);
    }
}
