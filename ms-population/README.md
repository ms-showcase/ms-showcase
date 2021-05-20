# Quarkus based Microservice from scratch

## Prerequisites
Installed:
* jdk (for example from: https://adoptopenjdk.net/installation.html)
* maven (https://maven.apache.org/install.html)
* gradle (https://gradle.org/install/)

## Intro
Quarkus - https://quarkus.io/

## Create project

inspiration:
* Quarkus project generation - https://quarkus.io/guides/openapi-swaggerui
* Gradle as build tool - https://quarkus.io/guides/gradle-tooling

Commands to execute:

```
mvn io.quarkus:quarkus-maven-plugin:1.13.0.Final:create \
-DprojectGroupId=io.population \
-DprojectArtifactId=ms-population \
-DclassName="io.population.PopulationResource" \
-Dpath="/population" \
-Dextensions="resteasy,resteasy-jackson,quarkus-smallrye-openapi,hibernate-orm-panache,hibernate-orm-rest-data-panache" \
-DbuildTool=gradle

cd ms-population

./gradlew build
```

## Check the generated app

* Quarkus URL: http://localhost:8080
* Swagger UI: http://localhost:8080/openapi-ui/index.html
* Test: PopulationResourceTest

## Test Driven Development

Make sure that:

* Slovak Republic population is accessible via API.
* List of countries whose population is provided is accessible via API.

## Add data

Inspiration:
* https://quarkus.io/guides/flyway#setting-up-support-for-flyway
* https://flywaydb.org/documentation/concepts/migrations.html#sql-based-migrations

Extend `build.gradle` with:
```
implementation 'io.quarkus:quarkus-flyway'
implementation 'io.quarkus:quarkus-jdbc-postgresql'
```

Extend `src/main/resources/application.properties` with:
```
quarkus.flyway.migrate-at-start=true
```

Download existing dataset - https://datahub.io/JohnSnowLabs/population-figures-by-country#resource-population-figures-by-country-csv

Introduce file: `db/migration/V1__Load_population_data.sql`:
```
CREATE TABLE population (
    country_code VARCHAR(3),
    country VARCHAR(100),
    population BIGINT,
    PRIMARY KEY (country_code)
);

```
Add Population data to file `db/migration/V1__Load_population_data.sql` (last year only):
```
cat population-figures-by-country-csv_csv.csv \
| tail -n +2 \
| sed "s/'/''/g" \
| awk -F '"' '{ if(index($2, ",") != 0) { gsub(",", ";", $2 ); print $2''$3} else print $0 }' \
| awk -F ',' '{ gsub (";",",", $1); if($2=="ERI") {$59=$54} print "INSERT INTO population (country, country_code, population) VALUES ('\''"$1"'\'','\''"$2"'\'',"$59");"}' >> V1__Load_population_data.sql
```

## Lombok - generate syntactic sugar

Extend `build.gradle` with:
```
plugins {
  id "io.freefair.lombok" version "5.3.0"
}
```

## REST API

Inspiration:
* https://quarkus.io/guides/rest-data-panache#hibernate-orm


Add Entity
```
package io.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Population {
    private String country;
    @Id
    private String countryCode;
    private long population;
}

```
Add Repository `PopulationRepository.java`:
```
package io.population;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PopulationRepository implements PanacheRepositoryBase<Population, String> {
}

```
Add Resource `PopulationResource.java`:

```
package io.population;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;

public interface PopulationResource extends PanacheRepositoryResource<PopulationRepository, Population, String> {
}
```