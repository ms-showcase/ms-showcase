# ms-showcase

Running via docker compose:
```
docker-compose \
-f docker-compose.yml \
-f docker-compose-ms-covid.yml \
-f docker-compose-ms-covid-data-loader.yml \
-f docker-compose-ms-population.yml \
-f docker-compose-ms-bff.yml \
up
```