package io.covid.db.repository;

import io.covid.db.model.CovidData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CovidDataRepository extends MongoRepository<CovidData, String> {
}
