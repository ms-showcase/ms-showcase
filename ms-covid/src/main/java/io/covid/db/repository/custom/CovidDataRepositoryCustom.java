package io.covid.db.repository.custom;

import java.util.List;
import io.covid.db.model.CovidData;

public interface CovidDataRepositoryCustom {
    List<CovidData> findWeekOldRecordsByDateAndIso(String isoCode, String date);
    List<String> findDistinctIsoCode();
    List<CovidData> lastYearStatistics(String isoCode);
}
