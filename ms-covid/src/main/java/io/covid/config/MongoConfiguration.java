package io.covid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "io.covid.db.repository")
@Configuration
public class MongoConfiguration {

}
