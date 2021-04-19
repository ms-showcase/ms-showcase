package io.covid.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CovidStatistics {
    private String date;
    private String newCases;
    private String icuPatients;
    private String newDeaths;
}
