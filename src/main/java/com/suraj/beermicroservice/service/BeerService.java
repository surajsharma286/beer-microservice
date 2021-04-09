package com.suraj.beermicroservice.service;

import com.suraj.beermicroservice.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    public BeerDto getById(UUID id);

    public BeerDto saveNewBeer(BeerDto beerDto);

    public BeerDto updateBeer(UUID id ,BeerDto beerDto);
}
