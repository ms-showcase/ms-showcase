#!/usr/bin/env bash
set -e

compose="-f docker-compose.yml"

for ms in "$@"
do
    echo "$ms"
    pushd "$ms"; gradle build ;  docker build -t msshowcase/"$ms" .; popd;
    if  [ "$ms" != "ms-cloud-config" ]; then
      compose="${compose} -f docker-compose-${ms}.yml"
    fi
done


echo "docker-compose $compose up"
eval "docker-compose $compose up"