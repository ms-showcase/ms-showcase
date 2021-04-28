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
    @Column(name = "country_code")
    private String countryCode;
    private long population;
}
