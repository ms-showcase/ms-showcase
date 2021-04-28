package io.covid.db.repository.custom;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import io.covid.db.model.CovidData;
import io.covid.dto.CovidStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CovidDataRepositoryCustomImpl implements CovidDataRepositoryCustom {
    public static final String ISO_CODE = "isoCode";
    public static final String DATE = "date";
    public static final String NEW_CASES = "newCases";
    public static final String ICU_PATIENTS = "icuPatients";
    public static final String NEW_DEATHS = "newDeaths";

    private MongoTemplate mongoTemplate;

    @Autowired
    public CovidDataRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CovidData> findWeekOldRecordsByDateAndIso(String isoCode, String date) {
        Query query = new Query();
        query.fields().include(DATE, NEW_CASES);
        query.addCriteria(Criteria.where(ISO_CODE).is(isoCode).and(DATE).lt(LocalDate.parse(date))
            .gt(LocalDate.parse(date).minusDays(7)));
        return mongoTemplate.find(query, CovidData.class).stream()
            .sorted(Comparator.comparing(CovidData::getDate)).collect(Collectors.toList());
    }

    @Override
    public List<String> findDistinctIsoCode() {
        Query query = new Query();
        query.fields().include(ISO_CODE);
        return mongoTemplate.findDistinct(query, ISO_CODE, CovidData.class, String.class);
    }

    @Override
    public List<CovidData> lastYearStatistics(String isoCode) {
        Query query = new Query();
        query.fields().include(DATE, NEW_CASES, ICU_PATIENTS, NEW_DEATHS);
        query.addCriteria(
            Criteria.where(ISO_CODE).is(isoCode).and(DATE).gt(LocalDate.now().minusMonths(12)));
        return mongoTemplate.find(query, CovidData.class).stream()
            .sorted(Comparator.comparing(CovidData::getDate)).collect(Collectors.toList());
    }

}
