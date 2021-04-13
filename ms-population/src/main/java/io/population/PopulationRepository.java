package io.population;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PopulationRepository implements PanacheRepositoryBase<Population, String> {
}
