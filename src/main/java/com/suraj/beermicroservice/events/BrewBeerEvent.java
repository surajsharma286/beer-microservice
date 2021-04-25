package com.suraj.beermicroservice.events;

import com.suraj.beermicroservice.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
