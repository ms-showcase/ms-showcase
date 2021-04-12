package io.covid.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class CovidData {
    @Id
    private String isoCodeAndDate;
    private String continent;
    private String location;
    private String totalCases;
    private String newCases;
    private String newCasesSmoothed;
    private String totalDeaths;
    private String newDeaths;
    private String newDeathsSmoothed;
    private String totalCasesPerMillion;
    private String newCasesPerMillion;
    private String newCasesSmoothedPerMillion;
    private String totalDeathsPerMillion;
    private String newDeathsPerMillion;
    private String newDeathsSmoothedPerMillion;
    private String reproductionRate;
    private String icuPatients;
    private String icuPatientsPerMillion;
    private String hospPatients;
    private String hospPatientsPerMillion;
    private String weeklyIcuAdmissions;
    private String weeklyIcuAdmissionsPerMillion;
    private String weeklyHospAdmissions;
    private String weeklyHospAdmissionsPerMillion;
    private String newTests;
    private String totalTests;
    private String totalTestsPerThousand;
    private String newTestsPerThousand;
    private String newTestsSmoothed;
    private String newTestsSmoothedPerThousand;
    private String positiveRate;
    private String testsPerCase;
    private String testsUnits;
    private String totalVaccinations;
    private String peopleVaccinated;
    private String peopleFullyVaccinated;
    private String newVaccinations;
    private String newVaccinationsSmoothed;
    private String totalVaccinationsPerHundred;
    private String peopleVaccinatedPerHundred;
    private String peopleFullyVaccinatedPerHundred;
    private String newVaccinationsSmoothedPerMillion;
    private String stringencyIndex;
    private String population;
    private String populationDensity;
    private String medianAge;
    private String aged65Older;
    private String aged70Older;
    private String gdpPerCapita;
    private String extremePoverty;
    private String cardiovascDeathRate;
    private String diabetesPrevalence;
    private String femaleSmokers;
    private String maleSmokers;
    private String handwashingFacilities;
    private String hospitalBedsPerThousand;
    private String lifeExpectancy;
    private String humanDevelopmentIndex;
}
