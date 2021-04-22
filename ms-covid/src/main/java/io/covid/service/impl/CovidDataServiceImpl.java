package io.covid.service.impl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import io.covid.db.model.CovidData;
import io.covid.db.repository.CovidDataRepository;
import io.covid.dto.CovidStatistics;
import io.covid.dto.chart.ChartData;
import io.covid.dto.chart.Series;
import io.covid.service.CovidDataService;
import io.covid.util.Utils;
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

    @Override public Series lastYearStatistics(String iso) {
        Optional<List<CovidData>> statistics = Optional.of(covidDataRepository.lastYearStatistics(iso));
        if(statistics.isPresent()){
            List<CovidData> covidData = statistics.get();
            List<CovidData> collect = covidData.stream()
                .collect(groupingBy(covidData1 -> covidData1.getDate().getMonth())).values()
                .stream().map(data -> data.stream().reduce(
                    (covidData1, covidData2) -> CovidData.builder().date(covidData1.getDate())
                        .icuPatients(Utils.sum(covidData1.getIcuPatients(), covidData2.getIcuPatients()))
                        .newCases(Utils.sum(covidData1.getNewCases(), covidData2.getNewCases()))
                        .newDeaths(Utils.sum(covidData1.getNewDeaths(), covidData2.getNewDeaths())).build()))
                .map(Optional::get).collect(toList());

            List<CovidStatistics> covidStats = collect.stream()
                .sorted(Comparator.comparing(CovidData::getDate)).map(
                    covidData1 -> CovidStatistics.builder()
                        .date(covidData1.getDate().getMonth().name() + " " +covidData1.getDate().getYear())
                        .newDeaths(covidData1.getNewDeaths())
                        .icuPatients(covidData1.getIcuPatients()).newCases(covidData1.getNewCases())
                        .build()).collect(toList());


            List<String> xaxis = covidStats.stream().map(CovidStatistics::getDate).collect(toList());
            ChartData newCases = ChartData.builder()
                .name("New Cases")
                .data(covidStats.stream().map(covidStatistics -> (int) Double.parseDouble(covidStatistics.getNewCases())).collect(Collectors.toList()))
                .build();

            ChartData icuPatients = ChartData.builder()
                .name("ICU Patients")
                .data(covidStats.stream().map(covidStatistics -> (int) Double.parseDouble(covidStatistics.getIcuPatients())).collect(Collectors.toList()))
                .build();

            ChartData newDeaths = ChartData.builder()
                .name("New Deaths")
                .data(covidStats.stream().map(covidStatistics -> (int) Double.parseDouble(covidStatistics.getNewDeaths())).collect(Collectors.toList()))
                .build();

            return Series.builder().xaxis(xaxis).series(Arrays.asList(newCases,icuPatients,newDeaths)).build();
        }
        return Series.builder().build();
    }

}
