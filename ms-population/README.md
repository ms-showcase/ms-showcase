# Quarkus based Microservice from scratch

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

## Develop Test


* test driven
    * all java country codes are covered
    * API available
    * API docs are available


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

1. opentracing support

```
./gradlew addExtension --extensions=com.github.fmcejudo:smallrye-opentracing
```

Extend `application.properties` with:
```
quarkus.jaeger.service-name=ms-population
quarkus.jaeger.sampler-type=const
quarkus.jaeger.sampler-param=1
quarkus.jaeger.endpoint=http://localhost:14268/api/traces
#quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, parentId=%X{parentId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n
quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n
```


1. Eureka integration

```
./gradlew addExtension --extensions=com.github.fmcejudo:quarkus-eureka:0.0.10
./gradlew addExtension --extensions=quarkus-rest-client
```

Extend `build.gradle` with:
```
implementation 'io.quarkus:quarkus-rest-client'
```

1. spring cloud config integration

```
./gradlew addExtension --extensions=spring-cloud-config-client

```