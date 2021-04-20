#!/usr/bin/env bash
set -e

compose="-f docker-compose.yml"

for ms in "$@"
do
    echo "$ms"
    pushd "$ms"; gradle build
    if  [ "$ms" != "ms-population" ]; then
      docker build -t msshowcase/"$ms" .; popd;
    else
      docker build -t msshowcase/"$ms" -f src/main/docker/Dockerfile.jvm .; popd;
    fi

    if  [ "$ms" != "ms-cloud-config" ]; then
      compose="${compose} -f docker-compose-${ms}.yml"
    fi
done


echo "docker-compose $compose up"
eval "docker-compose $compose up"