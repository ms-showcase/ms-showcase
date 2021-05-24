# ms-showcase

## Running via docker compose

To have up-to-date docker images, run:
```
docker compose \
-f docker-compose.yml \
-f docker-compose-ms-covid.yml \
-f docker-compose-ms-covid-data-loader.yml \
-f docker-compose-ms-population.yml \
-f docker-compose-ms-bff.yml \
pull
```
and then run the services via:
```
docker compose \
-f docker-compose.yml \
-f docker-compose-ms-covid.yml \
-f docker-compose-ms-covid-data-loader.yml \
-f docker-compose-ms-population.yml \
-f docker-compose-ms-bff.yml \
up
```

## Using the app

Once started:
* ms-showcase is accessible at: http://localhost:8093/ and
* Jaeger UI accessible at: http://localhost:16686/ .