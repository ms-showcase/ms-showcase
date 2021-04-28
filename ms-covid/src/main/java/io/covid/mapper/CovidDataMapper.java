package io.covid.mapper;

import io.covid.db.model.CovidData;
import io.covid.dto.CovidDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CovidDataMapper {

    CovidDataMapper INSTANCE = Mappers.getMapper(CovidDataMapper.class);

    @Mapping(target = "isoCodeAndDate", ignore = true)
    CovidData covidDataDtoToCovidDataModel(CovidDto covidDto);

    @AfterMapping
    default void setCovidDataId(@MappingTarget CovidData covidData, CovidDto covidDto){
        covidData.setIsoCodeAndDate(covidDto.getIsoCode() + "_" + covidDto.getDate().toString());
    }
}
