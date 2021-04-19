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
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<CovidData> findWeekOldRecordsByDateAndIso(String isoCode, String date) {
        Query query = new Query();
        query.fields().include("date","newCases");
        query.addCriteria(
            Criteria.where("isoCode").is(isoCode).and("date").lt(LocalDate.parse(date))
                .gt(LocalDate.parse(date).minusDays(7)));
        return mongoTemplate.find(query, CovidData.class).stream().sorted(Comparator.comparing(CovidData::getDate)).collect(
            Collectors.toList());
    }

    @Override
    public List<String> findDistinctIsoCode() {
        Query query = new Query();
        query.fields().include("isoCode");
        return mongoTemplate.findDistinct(query, "isoCode", CovidData.class, String.class);
    }

    @Override
    public List<CovidData> lastYearStatistics(String isoCode) {
        Query query = new Query();
        query.fields().include("date","newCases", "icuPatients", "newDeaths");
        query.addCriteria(
            Criteria.where("isoCode").is(isoCode).and("date")
                .gt(LocalDate.now().minusMonths(12)));
        return mongoTemplate.find(query, CovidData.class).stream().sorted(Comparator.comparing(CovidData::getDate)).collect(
            Collectors.toList());
    }

}
