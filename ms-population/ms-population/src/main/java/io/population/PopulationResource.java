package io.population;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;

public interface PopulationResource extends PanacheRepositoryResource<PopulationRepository, Population, String> {
}