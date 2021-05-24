package com.suraj.beermicroservice.model;

import guru.sfg.brewery.model.BeerStyleEnum;
import guru.sfg.brewery.model.BeerDto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("BeerName")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc("123123").build();

    }
}
