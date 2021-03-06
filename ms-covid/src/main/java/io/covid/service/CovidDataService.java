package io.covid.service;

import java.util.List;
import java.util.Optional;
import io.covid.db.model.CovidData;
import io.covid.dto.chart.Series;

public interface CovidDataService {

    Optional<CovidData> searchByCovidDataId(final String id);
    Optional<List<CovidData>> findWeekOldRecordsByDateAndIso(final String iso, final String date);
    Optional<List<String>> fetchDistinctIsoCode();
    Series lastYearStatistics(final String iso);
}
