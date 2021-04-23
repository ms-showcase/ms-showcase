package io.msdemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PopulationDto {
    private String country;
    private String countryCode;
    private String population;
}
