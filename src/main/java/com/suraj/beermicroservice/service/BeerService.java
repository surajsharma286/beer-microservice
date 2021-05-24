package com.suraj.beermicroservice.service;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPageList;
import guru.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    public BeerDto getById(UUID id,Boolean showInventoryOnHand);

    public BeerDto saveNewBeer(BeerDto beerDto);

    public BeerDto updateBeer(UUID id ,BeerDto beerDto);

    BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

    BeerDto getBeerByUpc(String upc);
}
