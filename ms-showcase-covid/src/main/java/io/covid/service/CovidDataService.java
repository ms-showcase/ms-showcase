package io.covid.service;

import java.util.Optional;
import io.covid.db.model.CovidData;

public interface CovidDataService {

    Optional<CovidData> searchByCovidDataId(final String id);
}
