package io.covid.service.impl;

import java.util.List;
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

    @Override public Optional<List<CovidData>> findWeekOldRecordsByDateAndIso(String iso, String date) {
        return Optional.of(covidDataRepository.findWeekOldRecordsByDateAndIso(iso, date));
    }

    @Override public Optional<List<String>> fetchDistinctIsoCode() {
        return Optional.of(covidDataRepository.findDistinctIsoCode());
    }

}
