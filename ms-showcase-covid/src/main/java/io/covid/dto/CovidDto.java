package io.covid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CovidDto {
    @JsonProperty("iso_code")
    private String isoCode;
    @JsonProperty("date")
    private String date;
    @JsonProperty("continent")
    private String continent;
    @JsonProperty("location")
    private String location;
    @JsonProperty("total_cases")
    private String totalCases;
    @JsonProperty("new_cases")
    private String newCases;
    @JsonProperty("new_cases_smoothed")
    private String newCasesSmoothed;
    @JsonProperty("total_deaths")
    private String totalDeaths;
    @JsonProperty("new_deaths")
    private String newDeaths;
    @JsonProperty("new_deaths_smoothed")
    private String newDeathsSmoothed;
    @JsonProperty("total_cases_per_million")
    private String totalCasesPerMillion;
    @JsonProperty("new_cases_per_million")
    private String newCasesPerMillion;
    @JsonProperty("new_cases_smoothed_per_million")
    private String newCasesSmoothedPerMillion;
    @JsonProperty("total_deaths_per_million")
    private String totalDeathsPerMillion;
    @JsonProperty("new_deaths_per_million")
    private String newDeathsPerMillion;
    @JsonProperty("new_deaths_smoothed_per_million")
    private String newDeathsSmoothedPerMillion;
    @JsonProperty("reproduction_rate")
    private String reproductionRate;
    @JsonProperty("icu_patients")
    private String icuPatients;
    @JsonProperty("icu_patients_per_million")
    private String icuPatientsPerMillion;
    @JsonProperty("hosp_patients")
    private String hospPatients;
    @JsonProperty("hosp_patients_per_million")
    private String hospPatientsPerMillion;
    @JsonProperty("weekly_icu_admissions")
    private String weeklyIcuAdmissions;
    @JsonProperty("weekly_icu_admissions_per_million")
    private String weeklyIcuAdmissionsPerMillion;
    @JsonProperty("weekly_hosp_admissions")
    private String weeklyHospAdmissions;
    @JsonProperty("weekly_hosp_admissions_per_million")
    private String weeklyHospAdmissionsPerMillion;
    @JsonProperty("new_tests")
    private String newTests;
    @JsonProperty("total_tests")
    private String totalTests;
    @JsonProperty("total_tests_per_thousand")
    private String totalTestsPerThousand;
    @JsonProperty("new_tests_per_thousand")
    private String newTestsPerThousand;
    @JsonProperty("new_tests_smoothed")
    private String newTestsSmoothed;
    @JsonProperty("new_tests_smoothed_per_thousand")
    private String newTestsSmoothedPerThousand;
    @JsonProperty("positive_rate")
    private String positiveRate;
    @JsonProperty("tests_per_case")
    private String testsPerCase;
    @JsonProperty("tests_units")
    private String testsUnits;
    @JsonProperty("total_vaccinations")
    private String totalVaccinations;
    @JsonProperty("people_vaccinated")
    private String peopleVaccinated;
    @JsonProperty("people_fully_vaccinated")
    private String peopleFullyVaccinated;
    @JsonProperty("new_vaccinations")
    private String newVaccinations;
    @JsonProperty("new_vaccinations_smoothed")
    private String newVaccinationsSmoothed;
    @JsonProperty("total_vaccinations_per_hundred")
    private String totalVaccinationsPerHundred;
    @JsonProperty("people_vaccinated_per_hundred")
    private String peopleVaccinatedPerHundred;
    @JsonProperty("people_fully_vaccinated_per_hundred")
    private String peopleFullyVaccinatedPerHundred;
    @JsonProperty("new_vaccinations_smoothed_per_million")
    private String newVaccinationsSmoothedPerMillion;
    @JsonProperty("stringency_index")
    private String stringencyIndex;
    @JsonProperty("population")
    private String population;
    @JsonProperty("population_density")
    private String populationDensity;
    @JsonProperty("median_age")
    private String medianAge;
    @JsonProperty("aged_65_older")
    private String aged65Older;
    @JsonProperty("aged_70_older")
    private String aged70Older;
    @JsonProperty("gdp_per_capita")
    private String gdpPerCapita;
    @JsonProperty("extreme_poverty")
    private String extremePoverty;
    @JsonProperty("cardiovasc_death_rate")
    private String cardiovascDeathRate;
    @JsonProperty("diabetes_prevalence")
    private String diabetesPrevalence;
    @JsonProperty("female_smokers")
    private String femaleSmokers;
    @JsonProperty("male_smokers")
    private String maleSmokers;
    @JsonProperty("handwashing_facilities")
    private String handwashingFacilities;
    @JsonProperty("hospital_beds_per_thousand")
    private String hospitalBedsPerThousand;
    @JsonProperty("life_expectancy")
    private String lifeExpectancy;
    @JsonProperty("human_development_index")
    private String humanDevelopmentIndex;
}
