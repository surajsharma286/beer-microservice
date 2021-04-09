package com.suraj.beermicroservice.mappers;

import com.suraj.beermicroservice.domain.Beer;
import com.suraj.beermicroservice.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
