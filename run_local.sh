#!/bin/bash
set -e

for MS in ms-bff ms-cloud-config ms-covid ms-population ms-covid-data-loader
do
  pushd $MS

  if test -f "gradlew"; then
    # we run also non-gradle apps here
    ./gradlew build
  fi

  if test -f "Dockerfile"; then
    docker build -t msshowcase/$MS .
  else
    # quarkus MSs are special
    docker build -t msshowcase/$MS -f ./src/main/docker/Dockerfile.jvm .
  fi



	popd
done

docker-compose \
-f docker-compose.yml \
-f docker-compose-ms-bff.yml \
-f docker-compose-ms-covid.yml \
-f docker-compose-ms-covid-data-loader.yml \
-f docker-compose-ms-population.yml \
up
