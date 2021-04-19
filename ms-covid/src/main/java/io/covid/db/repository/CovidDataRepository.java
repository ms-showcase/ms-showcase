package io.covid.db.repository;

import io.covid.db.model.CovidData;
import io.covid.db.repository.custom.CovidDataRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidDataRepository extends CovidDataRepositoryCustom,MongoRepository<CovidData, String> {

}
